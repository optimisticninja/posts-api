package ninja.optimistic.api.posts.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
@NoArgsConstructor
public class RestrictedAccessException extends RuntimeException {}
