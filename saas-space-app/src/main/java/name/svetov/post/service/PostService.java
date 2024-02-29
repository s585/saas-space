package name.svetov.post.service;

import name.svetov.post.model.Post;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface PostService {
    Publisher<Post> getOne(UUID postId);

    Publisher<Post> create(Post post);

    Publisher<Post> update(UUID postId, Post post);

    Publisher<UUID> delete(UUID postId);
}
