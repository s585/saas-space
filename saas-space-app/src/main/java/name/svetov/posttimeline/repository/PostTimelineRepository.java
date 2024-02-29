package name.svetov.posttimeline.repository;

import name.svetov.paging.model.Page;
import name.svetov.paging.model.Paging;
import org.reactivestreams.Publisher;

public interface PostTimelineRepository {
    Publisher<Page<String>> findAllByKey(String key, Paging paging);

    Publisher<Boolean> addEntry(String key, double score, String value);

    Publisher<Boolean> deleteEntry(String key, String value);
}
