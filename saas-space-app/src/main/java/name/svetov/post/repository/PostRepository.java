package name.svetov.post.repository;

import name.svetov.post.model.Post;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface PostRepository {
    Publisher<Post> getOne(UUID postId);

    Publisher<Post> add(Post post);

    Publisher<Post> update(UUID postId, Post post);

    Publisher<Post> delete(UUID postId);
}
