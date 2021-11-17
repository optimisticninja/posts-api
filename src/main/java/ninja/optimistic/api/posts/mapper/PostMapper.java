package ninja.optimistic.api.posts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ninja.optimistic.api.posts.generated.model.CreatePostRequest;
import ninja.optimistic.api.posts.model.Post;

@Mapper
public interface PostMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "created", ignore = true)
  @Mapping(target = "updated", ignore = true)
  @Mapping(target = "authorId", ignore = true)
  Post toEntity(CreatePostRequest createPostRequest);

  ninja.optimistic.api.posts.generated.model.Post toResource(Post post);
}
