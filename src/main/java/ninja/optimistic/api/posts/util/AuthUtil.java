package ninja.optimistic.api.posts.util;

import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@UtilityClass
public class AuthUtil {
  public Mono<Boolean> canAccess(ServerWebExchange exchange, String id, String localPermission) {
    Assert.notNull(id, "id must be non-null");
    Assert.notNull(localPermission, "localPermission must be non-null");
    Assert.isTrue(localPermission.contains(":l:"), "localPermission was not found to contain :l:");
    var authToken = exchange.getPrincipal().cast(JwtAuthenticationToken.class);
    var authorId = authToken.map(JwtAuthenticationToken::getName);
    var authorities = authToken.map(JwtAuthenticationToken::getAuthorities);
    var authorized = authorId.map(uid -> id.equals(uid));
    var global = localPermission.replace(":l:", ":*:");
    final var modifiedGlobal = global.substring(0, global.length() - 1) + "*";
    return Mono.zip(authorized, authorities)
        .map(
            authorizedAndAuthorities -> {
              var idMatches = authorizedAndAuthorities.getT1();
              var extractedAuthorities =
                  authorizedAndAuthorities.getT2().stream()
                      .map(GrantedAuthority::getAuthority)
                      .collect(Collectors.toSet())
                      .stream()
                      .filter(p -> p.equals(localPermission) || p.equals(modifiedGlobal))
                      .collect(Collectors.toList());

              if (!extractedAuthorities.isEmpty()) {
                var authority = extractedAuthorities.get(0);

                if ((authority.equals(localPermission) && idMatches)
                    || authority.equals(modifiedGlobal)) {
                  return true;
                }
              }

              return false;
            });
  }
}
