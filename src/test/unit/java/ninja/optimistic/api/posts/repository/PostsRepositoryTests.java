package ninja.optimistic.api.posts.repository;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import ninja.optimistic.api.posts.config.R2dbcConfig;
import ninja.optimistic.api.posts.model.Post;

@DataR2dbcTest
@ActiveProfiles("local")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(R2dbcConfig.class)
public class PostsRepositoryTests {
  @Autowired private PostsRepository postsRepository;

  @Test
  @WithMockUser
  public void testFindAllLike() {
    Post match =
        Post.builder()
            .summary("test")
            .title("test")
            .markdown("test")
            .imageUrl(URI.create("https://optimistic.ninja/test.png"))
            .build();
    Post nonMatch =
        Post.builder()
            .summary("foo")
            .title("foo")
            .markdown("foo")
            .imageUrl(URI.create("https://optimistic.ninja/foo.png"))
            .build();
    StepVerifier.create(postsRepository.save(match));
    StepVerifier.create(postsRepository.save(nonMatch));
    StepVerifier.create(postsRepository.findAllLike("test"))
        .expectNextCount(1)
        .expectNextMatches(p -> p.getSummary().equals("test"))
        .verifyComplete();
  }
}
