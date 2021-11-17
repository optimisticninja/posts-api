package ninja.optimistic.api.posts.model;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("posts")
@Value
@Builder(toBuilder = true)
public class Post {
  @Id
  @Column("id")
  private final UUID id;

  @Max(255)
  @CreatedBy
  @Column("author_id")
  private final String authorId;

  @CreatedDate
  @Column("created")
  private final OffsetDateTime created;

  @LastModifiedDate
  @Column("updated")
  private final OffsetDateTime updated;

  @Max(100)
  @NotBlank
  @Column("title")
  private final String title;

  @Max(200)
  @NotBlank
  @Column("summary")
  private final String summary;

  @NotBlank
  @Column("markdown")
  private final String markdown;

  @NotBlank
  @Column("image_url")
  private final URI imageUrl;
}
