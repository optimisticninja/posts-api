package ninja.optimistic.api.posts.generated.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.*;

/** CreatePostRequest */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreatePostRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("title")
  private String title;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("markdown")
  private String markdown;

  @JsonProperty("imageUrl")
  private URI imageUrl;

  public CreatePostRequest title(String title) {
    this.title = title;
    return this;
  }

  /**
   * post title
   *
   * @return title
   */
  @ApiModelProperty(
      example = "Harden your router with DD-WRT",
      required = true,
      value = "post title")
  @NotNull
  @Size(min = 1, max = 100)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public CreatePostRequest summary(String summary) {
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
      required = true,
      value = "post summary")
  @NotNull
  @Size(min = 1, max = 200)
  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public CreatePostRequest markdown(String markdown) {
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

  public CreatePostRequest imageUrl(URI imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  /**
   * image url
   *
   * @return imageUrl
   */
  @ApiModelProperty(
      example = "https://optimistic.ninja/logo.png",
      required = true,
      value = "image url")
  @NotNull
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
    CreatePostRequest createPostRequest = (CreatePostRequest) o;
    return Objects.equals(this.title, createPostRequest.title)
        && Objects.equals(this.summary, createPostRequest.summary)
        && Objects.equals(this.markdown, createPostRequest.markdown)
        && Objects.equals(this.imageUrl, createPostRequest.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, summary, markdown, imageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePostRequest {\n");

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
