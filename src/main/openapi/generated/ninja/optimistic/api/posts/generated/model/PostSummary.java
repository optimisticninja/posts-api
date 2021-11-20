package ninja.optimistic.api.posts.generated.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.*;

/** PostSummary */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PostSummary implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("title")
  private String title;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("imageUrl")
  private URI imageUrl;

  public PostSummary id(UUID id) {
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

  public PostSummary title(String title) {
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

  public PostSummary summary(String summary) {
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

  public PostSummary imageUrl(URI imageUrl) {
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
    PostSummary postSummary = (PostSummary) o;
    return Objects.equals(this.id, postSummary.id)
        && Objects.equals(this.title, postSummary.title)
        && Objects.equals(this.summary, postSummary.summary)
        && Objects.equals(this.imageUrl, postSummary.imageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, summary, imageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostSummary {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
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
