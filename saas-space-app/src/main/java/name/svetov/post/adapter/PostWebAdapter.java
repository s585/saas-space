package name.svetov.post.adapter;

import name.svetov.post.dto.PostDto;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface PostWebAdapter {
    Publisher<PostDto> get(UUID postId);

    Publisher<PostDto> create(PostDto post);

    Publisher<PostDto> update(UUID postId, PostDto post);

    Publisher<UUID> delete(UUID postId);
}
