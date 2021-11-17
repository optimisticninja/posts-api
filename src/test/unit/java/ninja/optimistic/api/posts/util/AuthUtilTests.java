package ninja.optimistic.api.posts.util;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class AuthUtilTests {
  @Mock private ServerWebExchange exchange;
  private static final Map<String, Object> TEST_MAP = Map.of("test", "test");
  private static final Jwt JWT = new Jwt("NOTAREALTOKEN", null, null, TEST_MAP, TEST_MAP);

  @Test
  public void givenValidLocalAuthorityAndUser_whenCanAccess_thenTrue() {
    var userId = "test";
    var scope = "SCOPE_test:l:w";
    var grantedAuthorities = List.of(new SimpleGrantedAuthority(scope));
    var principal = new JwtAuthenticationToken(JWT, grantedAuthorities, userId);
    when(exchange.getPrincipal()).thenReturn(Mono.just(principal));
    StepVerifier.create(AuthUtil.canAccess(exchange, userId, scope))
        .expectNext(true)
        .verifyComplete();
  }

  @Test
  public void givenInvalidLocalAuthority_whenCanAccess_thenFalse() {
    var userId = "test";
    var scope = "SCOPE_test:l:w";
    var grantedAuthorities = List.of(new SimpleGrantedAuthority(scope));
    var principal = new JwtAuthenticationToken(JWT, grantedAuthorities, userId);
    when(exchange.getPrincipal()).thenReturn(Mono.just(principal));
    StepVerifier.create(AuthUtil.canAccess(exchange, userId, "SCOPE_test:l:r"))
        .expectNext(false)
        .verifyComplete();
  }

  @Test
  public void givenInvalidUser_whenCanAccess_thenFalse() {
    var userId = "test";
    var scope = "SCOPE_test:l:w";
    var grantedAuthorities = List.of(new SimpleGrantedAuthority(scope));
    var principal = new JwtAuthenticationToken(JWT, grantedAuthorities, userId);
    when(exchange.getPrincipal()).thenReturn(Mono.just(principal));
    StepVerifier.create(AuthUtil.canAccess(exchange, "test2", "SCOPE_test:l:r"))
        .expectNext(false)
        .verifyComplete();
  }
}
