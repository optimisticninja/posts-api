package ninja.optimistic.api.posts.generated.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.*;

/** ListPostsResponse */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@lombok.Builder(toBuilder = true)
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListPostsResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("posts")
  @Valid
  private List<Post> posts = new ArrayList<>();

  @JsonProperty("nextPage")
  private Long nextPage;

  @JsonProperty("pageCount")
  private Long pageCount;

  public ListPostsResponse posts(List<Post> posts) {
    this.posts = posts;
    return this;
  }

  public ListPostsResponse addPostsItem(Post postsItem) {
    this.posts.add(postsItem);
    return this;
  }

  /**
   * list of posts
   *
   * @return posts
   */
  @ApiModelProperty(required = true, value = "list of posts")
  @NotNull
  @Valid
  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public ListPostsResponse nextPage(Long nextPage) {
    this.nextPage = nextPage;
    return this;
  }

  /**
   * next page minimum: 1
   *
   * @return nextPage
   */
  @ApiModelProperty(value = "next page")
  @Min(1L)
  public Long getNextPage() {
    return nextPage;
  }

  public void setNextPage(Long nextPage) {
    this.nextPage = nextPage;
  }

  public ListPostsResponse pageCount(Long pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  /**
   * number of pages minimum: 1
   *
   * @return pageCount
   */
  @ApiModelProperty(required = true, value = "number of pages")
  @NotNull
  @Min(1L)
  public Long getPageCount() {
    return pageCount;
  }

  public void setPageCount(Long pageCount) {
    this.pageCount = pageCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListPostsResponse listPostsResponse = (ListPostsResponse) o;
    return Objects.equals(this.posts, listPostsResponse.posts)
        && Objects.equals(this.nextPage, listPostsResponse.nextPage)
        && Objects.equals(this.pageCount, listPostsResponse.pageCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(posts, nextPage, pageCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListPostsResponse {\n");

    sb.append("    posts: ").append(toIndentedString(posts)).append("\n");
    sb.append("    nextPage: ").append(toIndentedString(nextPage)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
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
