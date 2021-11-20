package ninja.optimistic.api.posts.controller;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ninja.optimistic.api.posts.config.SecurityConfig;
import ninja.optimistic.api.posts.generated.model.*;
import ninja.optimistic.api.posts.mapper.PostMapper;
import ninja.optimistic.api.posts.mapper.PostMapperImpl;
import ninja.optimistic.api.posts.repository.PostsRepository;
import ninja.optimistic.api.posts.service.PostsService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("local")
@WebFluxTest(
    controllers = PostsController.class,
    excludeAutoConfiguration = ReactiveSecurityAutoConfiguration.class)
@Import({SecurityConfig.class, PostMapperImpl.class})
public class PostsControllerTests {
  @SpyBean private PostMapper mapper;
  @MockBean private PostsRepository repository;
  @MockBean private PostsService service;
  @Autowired private WebTestClient client;

  private final ninja.optimistic.api.posts.model.Post POST =
      ninja.optimistic.api.posts.model.Post.builder()
          .authorId("auth0|user")
          .id(UUID.randomUUID())
          .created(OffsetDateTime.now(ZoneOffset.UTC))
          .updated(OffsetDateTime.now(ZoneOffset.UTC))
          .imageUrl(URI.create("http://localhost/test.png"))
          .markdown("# Test")
          .title("test")
          .summary("test")
          .build();

  @Test
  @WithMockUser(authorities = "SCOPE_posts:g:w")
  void givenAdmin_whenCreatePost_thenSuccessfullyCreated() {
    var createPostRequest =
        CreatePostRequest.builder()
            .title(POST.getTitle())
            .summary(POST.getSummary())
            .markdown(POST.getMarkdown())
            .imageUrl(POST.getImageUrl())
            .build();
    when(service.createPost(createPostRequest)).thenReturn(Mono.just(POST));
    client
        .mutateWith(csrf())
        .post()
        .uri("/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(createPostRequest)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .location("/v1/posts/" + POST.getId());
    verify(service, times(1)).createPost(createPostRequest);
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:l:w")
  void givenCreator_whenCreatePost_thenSuccessfullyCreated() {
    var createPostRequest =
        CreatePostRequest.builder()
            .title(POST.getTitle())
            .summary(POST.getSummary())
            .markdown(POST.getMarkdown())
            .imageUrl(POST.getImageUrl())
            .build();
    when(service.createPost(createPostRequest)).thenReturn(Mono.just(POST));
    client
        .mutateWith(csrf())
        .post()
        .uri("/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(createPostRequest)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .location("/v1/posts/" + POST.getId());
    verify(service, times(1)).createPost(createPostRequest);
  }

  @Test
  void whenGetPosts_thenSuccessful() {
    when(repository.findAll(Sort.by("created").descending())).thenReturn(Flux.just(POST));
    client.get().uri("/posts").exchange().expectStatus().isOk();
    verify(repository, times(1)).findAll(Sort.by("created").descending());
  }

  @Test
  void givenQuery_whenGetPosts_thenSuccessful() {
    when(repository.findAllLike("test")).thenReturn(Flux.just(POST));
    client.mutateWith(csrf()).get().uri("/posts?query=test").exchange().expectStatus().isOk();
    verify(repository, times(1)).findAllLike("test");
  }

  @Test
  void whenGetPostsPaged_thenProperPageSize() {
    // Repeated only once because mapping of post creates initial post
    List<PostSummary> expectedPosts =
        Flux.just(mapper.toSummary(POST)).repeat(1).collectList().block();
    ListPostsResponse expectedResponse =
        ListPostsResponse.builder().postSummaries(expectedPosts).nextPage(1l).pageCount(2l).build();
    Flux<ninja.optimistic.api.posts.model.Post> persistedPosts = Flux.just(POST).repeat(2);
    when(repository.findAll(Sort.by("created").descending())).thenReturn(persistedPosts);
    client
        .mutateWith(csrf())
        .get()
        .uri("/posts?offset=0&size=2")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(ListPostsResponse.class)
        .isEqualTo(expectedResponse);
    verify(repository, times(1)).findAll(Sort.by("created").descending());
  }

  @Test
  void givenAnyUser_whenGetPostsInvalidPageSize_thenBadRequest() {
    when(repository.findAll(Sort.by("created").descending())).thenReturn(Flux.empty());
    client.get().uri("/posts?page=3&size=50").exchange().expectStatus().isBadRequest();
    verify(repository, times(1)).findAll(Sort.by("created").descending());
  }

  @Test
  void whenGetPostWithValidId_thenSuccessful() {
    var id = POST.getId();
    when(repository.findById(id)).thenReturn(Mono.just(POST));
    client
        .get()
        .uri("/posts/" + id)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(ninja.optimistic.api.posts.model.Post.class);
    verify(repository, times(1)).findById(id);
  }

  @Test
  void whenGetPostWithInValidId_thenNotFound() {
    var id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Mono.empty());
    client.get().uri("/posts/" + id).exchange().expectStatus().isNotFound();
    verify(repository, times(1)).findById(id);
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:g:w")
  void givenAdmin_whenDeletePost_thenSuccessfullyDeleted() {
    var id = POST.getId();
    when(repository.findById(id)).thenReturn(Mono.just(POST));
    when(repository.delete(POST)).thenReturn(Mono.empty());
    var post = repository.findById(id);
    client.mutateWith(csrf()).delete().uri("/posts/" + id).exchange().expectStatus().isNoContent();
    verify(repository, times(2)).findById(id);
    verify(repository, times(1)).delete(POST);
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:g:w")
  void givenAdmin_whenDeletePostWithInvalidId_thenNotFound() {
    var id = UUID.randomUUID();
    when(repository.findById(id)).thenReturn(Mono.empty());
    client.mutateWith(csrf()).delete().uri("/posts/" + id).exchange().expectStatus().isNotFound();
    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).delete(POST);
  }

  @Test
  void givenUnauthenticated_whenDeletePost_thenForbidden() {
    var id = POST.getId();
    client
        .mutateWith(csrf())
        .delete()
        .uri("/posts/" + id)
        .exchange()
        .expectStatus()
        .isUnauthorized();
    verify(repository, times(0)).findById(id);
    verify(repository, times(0)).delete(POST);
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:l:w")
  void givenUser_whenDeleteOtherUsersPost_thenForbidden() {
    var id = POST.getId();
    when(repository.findById(id)).thenReturn(Mono.just(POST));
    client.mutateWith(csrf()).delete().uri("/posts/" + id).exchange().expectStatus().isForbidden();
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:l:w")
  void givenCreator_whenDeleteOwnPost_thenSuccessfullyDeleted() {
    var myPost = POST.toBuilder().authorId("user").build();
    var id = myPost.getId();
    when(repository.findById(id)).thenReturn(Mono.just(myPost));
    when(repository.delete(myPost)).thenReturn(Mono.empty());
    client.mutateWith(csrf()).delete().uri("/posts/" + id).exchange().expectStatus().isNoContent();
    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).delete(myPost);
  }

  @Test
  void givenUnauthenticated_whenDeletePostWithInvalidId_thenForbidden() {
    var id = UUID.randomUUID();
    client
        .mutateWith(csrf())
        .delete()
        .uri("/posts/" + id)
        .exchange()
        .expectStatus()
        .isUnauthorized();
    verify(repository, times(0)).findById(id);
    verify(repository, times(0)).delete(POST);
  }

  @Test
  @WithMockUser(value = "user", authorities = "SCOPE_posts:g:w")
  void givenAdmin_whenUpdatePost_thenSuccessfullyUpdated() {
    var myPost = POST.toBuilder().authorId("user").build();
    var id = myPost.getId();
    var updatePostRequest =
        UpdatePostRequest.builder()
            .title("updated")
            .updateMask(Collections.singletonList(PostUpdateMask.TITLE))
            .build();
    when(repository.findById(id)).thenReturn(Mono.just(myPost));
    when(service.updatePost(id, updatePostRequest))
        .thenReturn(Mono.just(POST.toBuilder().markdown("updated").build()));
    client
        .mutateWith(csrf())
        .patch()
        .uri("/posts/" + id)
        .bodyValue(updatePostRequest)
        .exchange()
        .expectStatus()
        .isNoContent();
    verify(repository, times(1)).findById(id);
    verify(service, times(1)).updatePost(id, updatePostRequest);
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:l:w")
  void givenUser_whenUpdateOtherUsersPost_thenForbidden() {
    var id = POST.getId();
    var updatePostRequest =
        UpdatePostRequest.builder()
            .markdown("updated")
            .updateMask(Collections.singletonList(PostUpdateMask.MARKDOWN))
            .build();
    when(repository.findById(id)).thenReturn(Mono.just(POST));
    when(service.updatePost(id, updatePostRequest))
        .thenReturn(Mono.just(POST.toBuilder().markdown("updated").build()));
    client
        .mutateWith(csrf())
        .patch()
        .uri("/posts/" + id)
        .bodyValue(updatePostRequest)
        .exchange()
        .expectStatus()
        .isForbidden();
    verify(service, times(0)).updatePost(id, updatePostRequest);
    verify(repository, times(1)).findById(id);
    verify(repository, times(0)).delete(POST);
  }

  @Test
  @WithMockUser(authorities = "SCOPE_posts:g:w")
  void givenAdmin_whenUpdatePostWithInvalidId_thenNotFound() {
    var id = POST.getId();
    var updatePostRequest =
        UpdatePostRequest.builder()
            .title("updated")
            .updateMask(Collections.singletonList(PostUpdateMask.TITLE))
            .build();
    when(repository.findById(id)).thenReturn(Mono.empty());
    client
        .mutateWith(csrf())
        .patch()
        .uri("/posts/" + id)
        .bodyValue(updatePostRequest)
        .exchange()
        .expectStatus()
        .isNotFound();
    verify(repository, times(1)).findById(id);
  }

  @Test
  void givenUnauthenticated_whenUpdatePostWithValidId_thenForbidden() {
    var id = POST.getId();
    client
        .mutateWith(csrf())
        .patch()
        .uri("/posts/" + id)
        .bodyValue(
            UpdatePostRequest.builder()
                .title("updated")
                .updateMask(Collections.singletonList(PostUpdateMask.TITLE))
                .build())
        .exchange()
        .expectStatus()
        .isUnauthorized();
    verify(repository, times(0)).existsById(id);
  }

  @Test
  void givenUnauthenticated_whenUpdatePostWithInvalidId_thenForbidden() {
    var id = POST.getId();
    client
        .mutateWith(csrf())
        .patch()
        .uri("/posts/" + id)
        .bodyValue(
            UpdatePostRequest.builder()
                .title("updated")
                .updateMask(Collections.singletonList(PostUpdateMask.TITLE))
                .build())
        .exchange()
        .expectStatus()
        .isUnauthorized();
    verify(repository, times(0)).existsById(id);
  }
}
