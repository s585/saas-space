package name.svetov.post.converter;

import name.svetov.config.SaasMapperDefaultConfig;
import name.svetov.post.dto.PostDto;
import name.svetov.post.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SaasMapperDefaultConfig.class)
public interface PostConverter {
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Post map(PostDto source);

    PostDto map(Post source);
}
