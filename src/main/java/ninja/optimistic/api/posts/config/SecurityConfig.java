package ninja.optimistic.api.posts.config;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity(proxyTargetClass = true)
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {
  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;

  @Bean
  public SecurityWebFilterChain configure(ServerHttpSecurity http) {
    return http.exceptionHandling()
        .accessDeniedHandler(serverAccessDeniedHandler())
        .and()
        .authorizeExchange()
        .pathMatchers(
            HttpMethod.GET,
            "/swagger-ui.html",
            "/webjars/swagger-ui/*",
            "/v3/api-docs/swagger-config",
            "/v3/api-docs",
            "/openapi.yaml",
            "/openapi.json",
            "/actuator/health",
            "/posts",
            "/posts/*")
        .permitAll()
        .anyExchange()
        .authenticated()
        .and()
        .httpBasic()
        .disable()
        .formLogin()
        .disable()
        .csrf()
        .disable()
        .logout()
        .disable()
        .cors()
        .configurationSource(createCorsConfigSource())
        .and()
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .oauth2ResourceServer()
        .jwt()
        .and()
        .and()
        .build();
  }

  private ServerAccessDeniedHandler serverAccessDeniedHandler() {
    return (swe, e) -> {
      var name = swe.getPrincipal().map(Principal::getName);
      return swe.getPrincipal()
          .cast(AbstractAuthenticationToken.class)
          .map(AbstractAuthenticationToken::getAuthorities)
          .map(
              grantedAuthorities ->
                  grantedAuthorities.stream()
                      .map(GrantedAuthority::getAuthority)
                      .collect(Collectors.joining(",")))
          .zipWith(name)
          .flatMap(
              csvAndName -> {
                StringBuilder sb = new StringBuilder();
                sb.append("Authorization error [403]: Access Denied for ");
                sb.append(csvAndName.getT2());
                sb.append(": ");
                if (csvAndName.getT1().length() > 0) {
                  sb.append("found " + csvAndName.getT1());
                } else {
                  sb.append("no scopes found");
                }
                log.warn(sb.toString());
                return Mono.fromRunnable(
                    () -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN));
              });
    };
  }

  public CorsConfigurationSource createCorsConfigSource() {
    org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource source =
        new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("http://localhost:3000");
    config.setAllowedHeaders(
        List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.USER_AGENT, HttpHeaders.AUTHORIZATION));
    config.setAllowedMethods(
        List.of(
            HttpMethod.OPTIONS.name(),
            HttpMethod.GET.name(),
            HttpMethod.PUT.name(),
            HttpMethod.POST.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name()));
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  @Bean
  public WebSessionManager webSessionManager() {
    // Emulate SessionCreationPolicy.STATELESS
    return exchange -> Mono.empty();
  }

  @Bean
  public ReactiveJwtDecoder jwtDecoder() {
    return ReactiveJwtDecoders.fromOidcIssuerLocation(issuerUri);
  }
}
