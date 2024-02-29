package name.svetov.post.converter;

import name.svetov.post.dto.PostDto;
import name.svetov.post.model.Post;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
    componentModel = MappingConstants.ComponentModel.JAKARTA,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    imports = UUID.class
)
public interface PostConverter {
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Post map(PostDto source);

    PostDto map(Post source);
}
