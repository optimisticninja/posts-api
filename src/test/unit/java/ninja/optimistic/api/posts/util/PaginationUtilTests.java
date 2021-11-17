package ninja.optimistic.api.posts.util;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import ninja.optimistic.api.posts.exception.InvalidPageRequestException;

public class PaginationUtilTests {
  @Test
  public void givenAllPosts_whenNextPage_thenEmpty() {
    StepVerifier.create(PaginationUtil.nextPage(Mono.just(2L), Optional.of(1l), Optional.of(2l)))
        .expectNextCount(0)
        .verifyComplete();
  }

  @Test
  public void givenAllPosts_whenNextPage_thenNextPageReturned() {
    StepVerifier.create(PaginationUtil.nextPage(Mono.just(3L), Optional.of(0l), Optional.of(2l)))
        .expectNextMatches(next -> next.equals(1L))
        .verifyComplete();
  }

  @Test
  public void givenLargerCount_whenGetCount_thenProperPageSizeReturned() {
    StepVerifier.create(PaginationUtil.getCount(Mono.just(5L), Optional.of(2l)))
        .expectNextMatches(next -> next.equals(3L))
        .verifyComplete();
  }

  @Test
  public void givenCountSmallerThanSize_whenGetCount_thenCountIsOne() {
    StepVerifier.create(PaginationUtil.getCount(Mono.just(5L), Optional.of(2l)))
        .expectNextMatches(next -> next.equals(3L))
        .verifyComplete();
  }

  @Test
  public void givenInvalidPageRequest_whenValidated_thenInvalidPageRequestException() {
    StepVerifier.create(
            PaginationUtil.getPaged(Flux.just(0).repeat(3), Optional.of(2l), Optional.of(50l)))
        .expectError(InvalidPageRequestException.class)
        .verify();
  }

  @Test
  public void givenPageSizeLessThan0_whenValidated_thenInvalidPageRequestException() {
    StepVerifier.create(
            PaginationUtil.getPaged(Flux.just(0).repeat(3), Optional.of(-1l), Optional.of(50l)))
        .expectError(InvalidPageRequestException.class)
        .verify();
  }

  @Test
  public void givenPageNumberLessThan0_whenValidated_thenInvalidPageRequestException() {
    StepVerifier.create(
            PaginationUtil.getPaged(Flux.just(0).repeat(3), Optional.of(0l), Optional.of(-1l)))
        .expectError(InvalidPageRequestException.class)
        .verify();
  }
}
