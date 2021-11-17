package ninja.optimistic.api.posts.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import ninja.optimistic.api.posts.generated.model.CreatePostRequest;
import ninja.optimistic.api.posts.generated.model.PostUpdateMask;
import ninja.optimistic.api.posts.generated.model.UpdatePostRequest;
import ninja.optimistic.api.posts.mapper.PostMapper;
import ninja.optimistic.api.posts.model.Post;
import ninja.optimistic.api.posts.repository.PostsRepository;
import ninja.optimistic.api.posts.util.HTMLSanitizer;

@Service
@RequiredArgsConstructor
public class PostsService {
  private final PostMapper postMapper;
  private final PostsRepository postsRepository;

  public Mono<Post> createPost(CreatePostRequest request) {
    String sanitizedMarkdown = HTMLSanitizer.sanitize(request.getMarkdown());
    request.toBuilder().markdown(sanitizedMarkdown);
    Post post = postMapper.toEntity(request);
    return postsRepository.save(post);
  }

  public Mono<Post> updatePost(UUID postId, UpdatePostRequest request) {
    return postsRepository
        .findById(postId)
        .map(
            post -> {
              // Per Google API standards, if the mask is empty - we want to update all
              // fields
              List<PostUpdateMask> updateMask =
                  request.getUpdateMask() == null || request.getUpdateMask().isEmpty()
                      ? Arrays.asList(PostUpdateMask.class.getEnumConstants())
                      : request.getUpdateMask();

              for (PostUpdateMask mask : updateMask) {
                switch (mask) {
                  case TITLE:
                    post = post.toBuilder().title(request.getTitle()).build();
                    break;
                  case SUMMARY:
                    post = post.toBuilder().summary(request.getSummary()).build();
                    break;
                  case MARKDOWN:
                    post = post.toBuilder().markdown(request.getMarkdown()).build();
                    break;
                  case IMAGE_URL:
                    post = post.toBuilder().imageUrl(request.getImageUrl()).build();
                    break;
                  default:
                    break;
                }
              }
              return post;
            })
        .flatMap(postsRepository::save);
  }
}
