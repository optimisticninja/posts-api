package ninja.optimistic.api.posts.generated.model;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.hibernate.validator.constraints.*;

/** update mask for a post */
public enum PostUpdateMask {
  TITLE("TITLE"),

  SUMMARY("SUMMARY"),

  MARKDOWN("MARKDOWN"),

  IMAGE_URL("IMAGE_URL");

  private String value;

  PostUpdateMask(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PostUpdateMask fromValue(String value) {
    for (PostUpdateMask b : PostUpdateMask.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
