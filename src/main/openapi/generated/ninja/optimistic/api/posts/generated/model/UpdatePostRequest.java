package ninja.optimistic.api.posts.generated.model;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.*;

/** update post request */
@ApiModel(description = "update post request")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdatePostRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("title")
  private String title;

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("markdown")
  private String markdown;

  @JsonProperty("imageUrl")
  private URI imageUrl;

  @JsonProperty("updateMask")
  @Valid
  private List<PostUpdateMask> updateMask = new ArrayList<>();

  public UpdatePostRequest title(String title) {
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

  public UpdatePostRequest summary(String summary) {
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

  public UpdatePostRequest markdown(String markdown) {
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

  public UpdatePostRequest imageUrl(URI imageUrl) {
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

  public UpdatePostRequest updateMask(List<PostUpdateMask> updateMask) {
    this.updateMask = updateMask;
    return this;
  }

  public UpdatePostRequest addUpdateMaskItem(PostUpdateMask updateMaskItem) {
    this.updateMask.add(updateMaskItem);
    return this;
  }

  /**
   * Get updateMask
   *
   * @return updateMask
   */
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public List<PostUpdateMask> getUpdateMask() {
    return updateMask;
  }

  public void setUpdateMask(List<PostUpdateMask> updateMask) {
    this.updateMask = updateMask;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdatePostRequest updatePostRequest = (UpdatePostRequest) o;
    return Objects.equals(this.title, updatePostRequest.title)
        && Objects.equals(this.summary, updatePostRequest.summary)
        && Objects.equals(this.markdown, updatePostRequest.markdown)
        && Objects.equals(this.imageUrl, updatePostRequest.imageUrl)
        && Objects.equals(this.updateMask, updatePostRequest.updateMask);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, summary, markdown, imageUrl, updateMask);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdatePostRequest {\n");

    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    markdown: ").append(toIndentedString(markdown)).append("\n");
    sb.append("    imageUrl: ").append(toIndentedString(imageUrl)).append("\n");
    sb.append("    updateMask: ").append(toIndentedString(updateMask)).append("\n");
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
