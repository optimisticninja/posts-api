package ninja.optimistic.api.posts.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class InvalidPageRequestException extends Exception {
  public InvalidPageRequestException(String message) {
    super(message);
  }
}
