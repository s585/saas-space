package name.svetov.posttimeline.service;

import name.svetov.paging.model.Page;
import name.svetov.paging.model.Paging;
import name.svetov.post.model.Post;
import org.reactivestreams.Publisher;

import java.util.UUID;

public interface PostTimelineService {
    Publisher<Page<String>> getTimeline(Paging paging);

    Publisher<Boolean> addEntry(UUID userId, Post post);

    Publisher<Boolean> updateEntry(UUID userId, Post previousState, Post actualState);

    Publisher<Boolean> deleteEntry(UUID userId, Post post);
}
