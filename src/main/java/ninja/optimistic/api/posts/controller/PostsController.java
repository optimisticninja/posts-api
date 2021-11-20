package ninja.optimistic.api.posts.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ninja.optimistic.api.posts.exception.RestrictedAccessException;
import ninja.optimistic.api.posts.generated.controller.PostsApi;
import ninja.optimistic.api.posts.generated.model.CreatePostRequest;
import ninja.optimistic.api.posts.generated.model.ListPostsResponse;
import ninja.optimistic.api.posts.generated.model.PostSummary;
import ninja.optimistic.api.posts.generated.model.UpdatePostRequest;
import ninja.optimistic.api.posts.mapper.PostMapper;
import ninja.optimistic.api.posts.model.Post;
import ninja.optimistic.api.posts.repository.PostsRepository;
import ninja.optimistic.api.posts.service.PostsService;
import ninja.optimistic.api.posts.util.AuthUtil;
import ninja.optimistic.api.posts.util.PaginationUtil;

@RestController
@RequiredArgsConstructor
public class PostsController extends AbstractController implements PostsApi {
  private final PostMapper postMapper;
  private final PostsRepository postsRepository;
  private final PostsService postsService;

  @Override
  @PreAuthorize("hasAnyAuthority('SCOPE_posts:g:w', 'SCOPE_posts:l:w')")
  public Mono<ResponseEntity<Void>> createPost(
      @Valid Mono<CreatePostRequest> createPostRequest, ServerWebExchange exchange) {
    return createPostRequest
        .flatMap(postsService::createPost)
        .map(
            post ->
                UriComponentsBuilder.newInstance()
                    .path(getBasePath() + "/posts/{id}")
                    .buildAndExpand(post.getId())
                    .toUri())
        .map(ResponseEntity::created)
        .map(ResponseEntity.BodyBuilder::build);
  }

  @Override
  public Mono<ResponseEntity<ListPostsResponse>> getPosts(
      @Valid Optional<@Min(0L) Long> page,
      @Valid Optional<@Min(0L) @Max(100L) Long> size,
      @Valid Optional<@Size(min = 1) String> query,
      ServerWebExchange exchange) {
    Flux<Post> foundPosts;
    var sort = Sort.by("created").descending();

    // Execute query
    if (query.isPresent() && StringUtils.hasText(query.get())) {
      foundPosts = postsRepository.findAllLike(query.get()).cache();
    } else {
      foundPosts = postsRepository.findAll(sort).cache();
    }

    // Get paged posts and map to resource models
    Mono<List<PostSummary>> pagedResources =
        PaginationUtil.getPaged(foundPosts, page, size).map(postMapper::toSummary).collectList();

    // Get pagination metadata
    Mono<Long> nextPage = PaginationUtil.nextPage(foundPosts.count(), page, size);
    Mono<Long> pageCount = PaginationUtil.getCount(foundPosts.count(), size);

    // Build response
    Mono<ListPostsResponse> response =
        pagedResources.map(posts -> ListPostsResponse.builder().postSummaries(posts).build());
    response =
        Mono.zip(response, nextPage)
            .map(tuple -> tuple.getT1().toBuilder().nextPage(tuple.getT2()).build())
            .switchIfEmpty(response);
    response =
        Mono.zip(response, pageCount)
            .map(tuple -> tuple.getT1().toBuilder().pageCount(tuple.getT2()).build());
    return response.map(ResponseEntity::ok);
  }

  @Override
  public Mono<ResponseEntity<ninja.optimistic.api.posts.generated.model.Post>> getPost(
      UUID postId, ServerWebExchange exchange) {
    return postsRepository
        .findById(postId)
        .map(postMapper::toResource)
        .map(ResponseEntity::ok)
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @Override
  @PreAuthorize("hasAnyAuthority('SCOPE_posts:g:w', 'SCOPE_posts:l:w')")
  public Mono<ResponseEntity<Void>> updatePost(
      UUID postId, @Valid Mono<UpdatePostRequest> updatePostRequest, ServerWebExchange exchange) {
    Mono<ninja.optimistic.api.posts.model.Post> foundPost =
        postsRepository.findById(postId).cache();
    Mono<Boolean> authorized =
        foundPost.flatMap(
            post -> AuthUtil.canAccess(exchange, post.getAuthorId(), "SCOPE_posts:l:w"));
    Mono<ResponseEntity<Void>> response =
        authorized.flatMap(
            auth ->
                auth
                    ? updatePostRequest
                        .flatMap(request -> postsService.updatePost(postId, request))
                        .map(post -> ResponseEntity.noContent().build())
                    : Mono.error(RestrictedAccessException::new));
    return response.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @Override
  @PreAuthorize("hasAnyAuthority('SCOPE_posts:g:w', 'SCOPE_posts:l:w')")
  public Mono<ResponseEntity<Void>> deletePost(UUID postId, ServerWebExchange exchange) {
    ResponseEntity<Void> noContent = ResponseEntity.noContent().build();
    Mono<ninja.optimistic.api.posts.model.Post> foundPost =
        postsRepository.findById(postId).cache();
    Mono<Boolean> authorized =
        foundPost
            .flatMap(post -> AuthUtil.canAccess(exchange, post.getAuthorId(), "SCOPE_posts:l:w"))
            .cache();
    Mono<ResponseEntity<Void>> response =
        Mono.zip(authorized, foundPost)
            .flatMap(
                tuple2 ->
                    tuple2.getT1()
                        ? postsRepository.delete(tuple2.getT2()).thenReturn(noContent)
                        : Mono.error(RestrictedAccessException::new));
    return response.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }
}
