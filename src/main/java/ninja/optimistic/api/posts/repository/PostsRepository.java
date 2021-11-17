package ninja.optimistic.api.posts.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import ninja.optimistic.api.posts.model.Post;

public interface PostsRepository extends ReactiveSortingRepository<Post, UUID> {
  @Query(
      "SELECT * FROM posts "
          + "WHERE markdown @@ plainto_tsquery(:query) "
          + "OR title @@ plainto_tsquery(:query) "
          + "OR summary @@ plainto_tsquery(:query) ORDER BY created DESC")
  Flux<Post> findAllLike(@Param("query") String query);
}
