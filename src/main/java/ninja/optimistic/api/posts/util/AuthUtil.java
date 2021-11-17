package ninja.optimistic.api.posts.util;

import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@UtilityClass
public class AuthUtil {
  public Mono<Boolean> canAccess(ServerWebExchange exchange, String id, String localPermission) {
    Assert.notNull(id, "id must be non-null");
    Assert.notNull(localPermission, "localPermission must be non-null");
    Assert.isTrue(localPermission.contains(":l:"), "localPermission was not found to contain :l:");
    var authToken = exchange.getPrincipal().cast(AbstractAuthenticationToken.class);
    var authorId = authToken.map(AbstractAuthenticationToken::getName);
    var authorities = authToken.map(AbstractAuthenticationToken::getAuthorities);
    var authorized = authorId.map(uid -> id.equals(uid));
    var globalPermission = localPermission.replace(":l:", ":g:");
    return Mono.zip(authorized, authorities)
        .map(
            authorizedAndAuthorities -> {
              var idMatches = authorizedAndAuthorities.getT1();
              var extractedAuthorities =
                  authorizedAndAuthorities.getT2().stream()
                      .map(GrantedAuthority::getAuthority)
                      .collect(Collectors.toSet())
                      .stream()
                      .filter(p -> p.equals(localPermission) || p.equals(globalPermission))
                      .collect(Collectors.toList());

              if (!extractedAuthorities.isEmpty()) {
                var authority = extractedAuthorities.get(0);

                if ((authority.equals(localPermission) && idMatches)
                    || authority.equals(globalPermission)) {
                  return true;
                }
              }

              return false;
            });
  }
}
