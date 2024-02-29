package name.svetov.posttimeline.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.posttimeline.adapter.PostTimelineWebAdapter;
import name.svetov.posttimeline.dto.PostTimelineRq;
import name.svetov.posttimeline.dto.PostTimelineRs;
import org.reactivestreams.Publisher;

@Controller
@RequiredArgsConstructor
public class PostTimelineControllerImpl implements PostTimelineController {
    private final PostTimelineWebAdapter webAdapter;

    @Override
    public Publisher<PostTimelineRs> getTimeline(PostTimelineRq rq) {
        return webAdapter.getTimeline(rq);
    }
}
