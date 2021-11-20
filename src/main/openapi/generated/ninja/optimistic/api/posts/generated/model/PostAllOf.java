package ninja.optimistic.api.posts.generated.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.*;

/** PostAllOf */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PostAllOf implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("authorId")
  private String authorId;

  @JsonProperty("created")
  @org.springframework.format.annotation.DateTimeFormat(
      iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime created;

  @JsonProperty("updated")
  @org.springframework.format.annotation.DateTimeFormat(
      iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updated;

  @JsonProperty("markdown")
  private String markdown;

  public PostAllOf authorId(String authorId) {
    this.authorId = authorId;
    return this;
  }

  /**
   * author id
   *
   * @return authorId
   */
  @ApiModelProperty(
      example = "auth0|6192e1f238dad1006f2e4ce9",
      required = true,
      value = "author id")
  @NotNull
  @Size(max = 255)
  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public PostAllOf created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * date-time of post creation
   *
   * @return created
   */
  @ApiModelProperty(required = true, value = "date-time of post creation")
  @NotNull
  @Valid
  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public PostAllOf updated(OffsetDateTime updated) {
    this.updated = updated;
    return this;
  }

  /**
   * date-time of last post update
   *
   * @return updated
   */
  @ApiModelProperty(required = true, value = "date-time of last post update")
  @NotNull
  @Valid
  public OffsetDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }

  public PostAllOf markdown(String markdown) {
    this.markdown = markdown;
    return this;
  }

  /**
   * post markdown
   *
   * @return markdown
   */
  @ApiModelProperty(example = "# This is an H1", required = true, value = "post markdown")
  @NotNull
  @Size(min = 1)
  public String getMarkdown() {
    return markdown;
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostAllOf postAllOf = (PostAllOf) o;
    return Objects.equals(this.authorId, postAllOf.authorId)
        && Objects.equals(this.created, postAllOf.created)
        && Objects.equals(this.updated, postAllOf.updated)
        && Objects.equals(this.markdown, postAllOf.markdown);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorId, created, updated, markdown);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostAllOf {\n");

    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("    markdown: ").append(toIndentedString(markdown)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
