package ninja.optimistic.api.posts.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import ninja.optimistic.api.posts.generated.model.PostUpdateMask;
import ninja.optimistic.api.posts.generated.model.UpdatePostRequest;
import ninja.optimistic.api.posts.model.Post;
import ninja.optimistic.api.posts.repository.PostsRepository;

@ExtendWith(MockitoExtension.class)
public class PostsServiceTests {
  @Mock private PostsRepository repository;
  @InjectMocks private PostsService service;

  @Test
  public void givenValidUpdatePostRequest_whenUpdateMaskNotSet_thenAllPropertyUpdateSuccessful() {
    var id = UUID.randomUUID();
    var updatePostRequest =
        UpdatePostRequest.builder()
            .imageUrl(URI.create("https://optimistic.ninja/image.png"))
            .markdown("# Test")
            .summary("test")
            .title("test")
            .build();
    OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
    Post unmodifiedPost =
        Post.builder()
            .id(id)
            .authorId("auth0|test")
            .created(now)
            .updated(now)
            .title("unmodified")
            .imageUrl(URI.create("https://unmodified.unmodified"))
            .summary("unmodified")
            .markdown("unmodified")
            .build();
    when(repository.findById(id)).thenReturn(Mono.just(unmodifiedPost));
    Post updatedPost =
        unmodifiedPost.toBuilder()
            .markdown(updatePostRequest.getMarkdown())
            .title(updatePostRequest.getTitle())
            .imageUrl(updatePostRequest.getImageUrl())
            .summary(updatePostRequest.getSummary())
            .build();
    when(repository.save(updatedPost)).thenReturn(Mono.just(updatedPost));
    StepVerifier.create(service.updatePost(id, updatePostRequest))
        .expectNextMatches(post -> post.equals(updatedPost))
        .verifyComplete();
    Mockito.verify(repository, times(1)).findById(id);
    Mockito.verify(repository, times(1)).save(any());
  }

  @Test
  public void givenValidUpdatePostRequest_whenUpdateMaskSet_thenSuccessful() {
    var id = UUID.randomUUID();
    var updatePostRequest =
        UpdatePostRequest.builder()
            .title("modified")
            .updateMask(List.of(PostUpdateMask.TITLE))
            .build();
    OffsetDateTime now = OffsetDateTime.now();
    Post unmodifiedPost =
        Post.builder()
            .id(id)
            .authorId("auth0|test")
            .created(now)
            .updated(now)
            .title("unmodified")
            .build();
    when(repository.findById(id)).thenReturn(Mono.just(unmodifiedPost));
    Post updatedPost = unmodifiedPost.toBuilder().title(updatePostRequest.getTitle()).build();
    when(repository.save(updatedPost)).thenReturn(Mono.just(updatedPost));
    StepVerifier.create(service.updatePost(id, updatePostRequest))
        .expectNextMatches(post -> updatedPost.equals(post))
        .verifyComplete();
    Mockito.verify(repository, times(1)).findById(id);
    Mockito.verify(repository, times(1)).save(updatedPost);
  }
}
