package name.svetov.posttimeline.adapter;

import name.svetov.posttimeline.dto.PostTimelineRq;
import name.svetov.posttimeline.dto.PostTimelineRs;
import org.reactivestreams.Publisher;

public interface PostTimelineWebAdapter {
    Publisher<PostTimelineRs> getTimeline(PostTimelineRq rq);
}
