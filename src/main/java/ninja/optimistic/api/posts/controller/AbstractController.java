package ninja.optimistic.api.posts.controller;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public abstract class AbstractController {
  @Value("${spring.webflux.base-path}")
  @NotNull(message = "spring.webflux.base-path couldn't be retrieved from application.yml")
  private String basePath;
}
