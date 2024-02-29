package name.svetov.post.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.post.adapter.PostWebAdapter;
import name.svetov.post.dto.PostDto;
import org.reactivestreams.Publisher;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class PostControllerImpl implements PostController {
    private final PostWebAdapter webAdapter;

    @Override
    public Publisher<PostDto> get(UUID postId) {
        return webAdapter.get(postId);
    }

    @Override
    public Publisher<PostDto> create(PostDto post) {
        return webAdapter.create(post);
    }

    @Override
    public Publisher<PostDto> update(UUID postId, PostDto post) {
        return webAdapter.update(postId, post);
    }

    @Override
    public Publisher<UUID> delete(UUID postId) {
        return webAdapter.delete(postId);
    }
}
