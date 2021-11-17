package ninja.optimistic.api.posts.generated.model;

import java.io.Serializable;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.*;

/** Post */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Post implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private UUID id;

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

  @JsonProperty("title")
  private String title;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("markdown")
  private String markdown;

  @JsonProperty("imageUrl")
  private URI imageUrl;

  public Post id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * post id
   *
   * @return id
   */
  @ApiModelProperty(example = "046b6c7f-0b8a-43b9-b35d-6489e6daee91", value = "post id")
  @Valid
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Post authorId(String authorId) {
    this.authorId = authorId;
    return this;
  }

  /**
   * author id
   *
   * @return authorId
   */
  @ApiModelProperty(example = "auth0|6192e1f238dad1006f2e4ce9", value = "author id")
  @Size(max = 255)
  public String getAuthorId() {
    return authorId;
  }

  public void setAuthorId(String authorId) {
    this.authorId = authorId;
  }

  public Post created(OffsetDateTime created) {
    this.created = created;
    return this;
  }

  /**
   * date-time of post creation
   *
   * @return created
   */
  @ApiModelProperty(value = "date-time of post creation")
  @Valid
  public OffsetDateTime getCreated() {
    return created;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public Post updated(OffsetDateTime updated) {
    this.updated = updated;
    return this;
  }

  /**
   * date-time of last post update
   *
   * @return updated
   */
  @ApiModelProperty(value = "date-time of last post update")
  @Valid
  public OffsetDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }

  public Post title(String title) {
    this.title = title;
    return this;
  }

  /**
   * post title
   *
   * @return title
   */
  @ApiModelProperty(example = "Harden your router with DD-WRT", value = "post title")
  @Size(min = 1, max = 100)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Post summary(String summary) {
    this.summary = summary;
    return this;
  }

  /**
   * post summary
   *
   * @return summary
   */
  @ApiModelProperty(
      example = "Configure firewall, Pihole, WireGuard and other services on DD-WRT",
      value = "post summary")
  @Size(min = 1, max = 200)
  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Post markdown(String markdown) {
    this.markdown = markdown;
    return this;
  }

  /**
   * post markdown
   *
   * @return markdown
   */
  @ApiModelProperty(example = "# This is an H1", value = "post markdown")
  @Size(min = 1)
  public String getMarkdown() {
    return markdown;
  }

  public void setMarkdown(String markdown) {
    this.markdown = markdown;
  }

  public Post imageUrl(URI imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * image url
   *
   * @return imageUrl
   */
  @ApiModelProperty(example = "https://optimistic.ninja/logo.png", value = "image url")
  @Valid
  public URI getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(URI imageUrl) {
    this.imageUrl = imageUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return Objects.equals(this.id, post.id)
        && Objects.equals(this.authorId, post.authorId)
        && Objects.equals(this.created, post.created)
        && Objects.equals(this.updated, post.updated)
        && Objects.equals(this.title, post.title)
        && Objects.equals(this.summary, post.summary)
        && Objects.equals(this.markdown, post.markdown)
        && Objects.equals(this.imageUrl, post.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, authorId, created, updated, title, summary, markdown, imageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Post {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    markdown: ").append(toIndentedString(markdown)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
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
