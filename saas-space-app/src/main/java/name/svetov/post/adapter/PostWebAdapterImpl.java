package name.svetov.post.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.post.converter.PostConverter;
import name.svetov.post.dto.PostDto;
import name.svetov.post.service.PostService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class PostWebAdapterImpl implements PostWebAdapter {
    private final PostService postService;
    private final PostConverter converter;

    @Override
    public Publisher<PostDto> get(UUID postId) {
        return Mono.from(postService.getOne(postId))
            .map(converter::map);
    }

    @Override
    public Publisher<PostDto> create(PostDto post) {
        return Mono.from(postService.create(converter.map(post)))
                .map(converter::map);
    }

    @Override
    public Publisher<PostDto> update(UUID postId, PostDto post) {
        return Mono.from(postService.update(postId, converter.map(post)))
            .map(converter::map);
    }

    @Override
    public Publisher<UUID> delete(UUID postId) {
        return Mono.from(postService.delete(postId));
    }
}
