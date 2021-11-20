package ninja.optimistic.api.posts.repository;

import java.net.URI;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import reactor.test.StepVerifier;

import ninja.optimistic.api.posts.config.R2dbcConfig;
import ninja.optimistic.api.posts.model.Post;

@DataR2dbcTest()
@ActiveProfiles("local")
@Import(R2dbcConfig.class)
@ContextConfiguration(initializers = PostsRepositoryTests.Initializer.class)
public class PostsRepositoryTests {
  @Autowired private PostsRepository postsRepository;

  protected static PostgreSQLContainer postgreSQLContainer =
      new PostgreSQLContainer("postgres:13.4")
          .withDatabaseName("repository-tests-db")
          .withUsername("sa")
          .withPassword("sa");

  @BeforeAll
  public static void beforeAll() {
    postgreSQLContainer.start();
  }

  @AfterAll
  public static void afterAll() {
    postgreSQLContainer.stop();
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      TestPropertyValues.of(
              "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl().replace("jdbc", "r2dbc"),
              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
              "spring.datasource.password=" + postgreSQLContainer.getPassword(),
              "spring.flyway.url=" + postgreSQLContainer.getJdbcUrl(),
              "spring.flyway.user=" + postgreSQLContainer.getUsername(),
              "spring.flyway.password=" + postgreSQLContainer.getPassword())
          .applyTo(configurableApplicationContext.getEnvironment());
    }
  }

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
    StepVerifier.create(postsRepository.save(match)).expectNextCount(1).verifyComplete();
    StepVerifier.create(postsRepository.save(nonMatch)).expectNextCount(1).verifyComplete();
    StepVerifier.create(postsRepository.findAllLike("test"))
        .expectNextCount(1)
        .expectNextMatches(p -> p.getSummary().equals("test"))
        .verifyComplete();
  }
}
