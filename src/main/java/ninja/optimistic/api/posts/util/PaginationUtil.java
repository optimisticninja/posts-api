package ninja.optimistic.api.posts.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import ninja.optimistic.api.posts.exception.InvalidPageRequestException;

/**
 * Pagination utility class for requesting paged resources from a Flux.
 *
 * <p>Pagination is dealt with in terms of how the frontend requests it - meaning, the resource
 * layer request 1-indexed resources, not 0.
 */
@UtilityClass
@Slf4j
public class PaginationUtil {
  private static final String ERROR_PREFIX = "Invalid page request: ";
  private static final long PAGE_MIN = 0;
  private static final long SIZE_MAX = 100;

  /**
   * Get page offset or default to {@link #PAGE_MIN} if null
   *
   * @param page page provided in request
   * @return {@link #PAGE_MIN} or same value
   */
  private long getPageOffset(Optional<Long> page) {
    validatePage(page);
    long offsetOrDefault = page.isPresent() ? page.get() : PAGE_MIN;

    if (offsetOrDefault < 0) {
      throw new IllegalArgumentException("page must be >= 0");
    }

    return offsetOrDefault;
  }

  /**
   * Get size of page or default to {@link #SIZE_MAX} if null
   *
   * @param size size provided in request
   * @return {@link #SIZE_MAX} or same value
   */
  private long getPageSize(Optional<Long> size) {
    validateSize(size);
    long sizeOrDefault = size.isPresent() ? size.get() : SIZE_MAX;

    if (sizeOrDefault < 1) {
      throw new IllegalArgumentException("size must be >= 1");
    }

    return size.isPresent() ? size.get() : SIZE_MAX;
  }

  /**
   * Get the number of elements to skip
   *
   * @param page page provided in request
   * @param size size provided in request
   * @return number of elements to skip
   */
  private long getSkip(Optional<Long> page, Optional<Long> size)
      throws InvalidPageRequestException {
    Set<String> errors = new HashSet<>();
    long skipOrDefault = -1;

    try {
      skipOrDefault = (getPageOffset(page)) * getPageSize(size);
    } catch (IllegalArgumentException e) {
      errors.add(e.getMessage());
    }

    if (!errors.isEmpty()) {
      throw new InvalidPageRequestException(
          ERROR_PREFIX + errors.stream().collect(Collectors.joining(",")));
    }

    // Since skip counts by zero and the frontend counts by one, we subtract one
    return skipOrDefault;
  }

  /**
   * Get the number of elements to take
   *
   * @param size size provided in request
   * @return number of elements to take
   */
  private long getTake(Optional<Long> size) {
    return getPageSize(size);
  }

  /**
   * Return next page number if one exists, else an empty Mono
   *
   * @param count mono of flux count
   * @param page page provided in request
   * @param size size provided in request
   * @return next page number or an empty Mono
   */
  public Mono<Long> nextPage(Mono<Long> count, Optional<Long> page, Optional<Long> size) {
    return count.flatMap(
        cnt -> {
          try {
            return (cnt > (getSkip(page, size) + getTake(size))
                ? Mono.just(getPageOffset(page) + 1)
                : Mono.empty());
          } catch (InvalidPageRequestException e) {
            return Mono.error(e);
          }
        });
  }

  /**
   * Empty page and those starting at 1 or above are valid
   *
   * @param page page provided in request
   * @return is valid
   */
  private boolean validatePage(Optional<Long> page) {
    return page.isEmpty() || (page.isPresent() && page.get() >= 1) ? true : false;
  }

  /**
   * Empty size and those starting at 1 or above are valid
   *
   * @param size size provided in request
   * @return is valid
   */
  private boolean validateSize(Optional<Long> size) {
    return size.isEmpty() || (size.isPresent() && size.get() >= 1) ? true : false;
  }

  /**
   * Validates that the page/size are above 1 if not empty and that we don'e exceed page boundaries
   *
   * @param flux flux of objects
   * @param page page to be requested (1 indexed since material UI counts from the user's
   *     perspective)
   * @param size size of the page
   * @param <T> type of the objects in @flux
   * @return
   */
  private <T> Flux<T> validatePageRequest(Flux<T> flux, Optional<Long> page, Optional<Long> size) {
    Mono<Boolean> inPageBoundary =
        flux.count()
            .flatMap(
                cnt -> {
                  try {
                    return Mono.just(
                        getSkip(page, size) + (getTake(size) - getPageSize(size)) <= cnt);
                  } catch (InvalidPageRequestException e) {
                    return Mono.error(e);
                  }
                });
    return inPageBoundary.flatMapMany(
        inBounds ->
            inBounds
                ? flux
                : Flux.error(
                    new InvalidPageRequestException(ERROR_PREFIX + "out of page boundary")));
  }

  public <T> Flux<T> getPaged(Flux<T> flux, Optional<Long> page, Optional<Long> size) {
    try {
      return validatePageRequest(flux, page, size).skip(getSkip(page, size)).take(getTake(size));
    } catch (InvalidPageRequestException e) {
      return Flux.error(e);
    }
  }

  public Mono<Long> getCount(Mono<Long> count, Optional<Long> size) {
    boolean validSize = validateSize(size);
    Mono<Long> remainder = count.map(c -> c % getPageSize(size));
    Mono<Long> basePages = count.map(c -> c / getPageSize(size));
    return validSize
        ? Mono.zip(basePages, remainder)
            .map(tuple2 -> tuple2.getT2() > 0l ? tuple2.getT1() + 1 : tuple2.getT1())
        : Mono.error(new InvalidPageRequestException(ERROR_PREFIX + "size must be >= 1"));
  }
}
