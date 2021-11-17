package ninja.optimistic.api.posts.config;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

@Configuration
@EnableR2dbcAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class R2dbcConfig {
  @Bean
  public ReactiveAuditorAware<String> auditorProvider() {
    return () ->
        ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getName);
  }

  @Bean
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now(ZoneOffset.UTC));
  }
}
