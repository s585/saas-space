package name.svetov.posttimeline.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.paging.converter.PagingConverter;
import name.svetov.posttimeline.dto.PostTimelineRq;
import name.svetov.posttimeline.dto.PostTimelineRs;
import name.svetov.posttimeline.service.PostTimelineService;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class PostTimelineWebAdapterImpl implements PostTimelineWebAdapter {
    private final PostTimelineService postTimelineService;
    private final PagingConverter pagingConverter;

    @Override
    public Publisher<PostTimelineRs> getTimeline(PostTimelineRq rq) {
        return Mono.from(postTimelineService.getTimeline(pagingConverter.convert(rq.getPaging())))
            .map(
                page -> PostTimelineRs.builder()
                    .data(page.getData())
                    .paging(pagingConverter.convert(page.getPaging()))
                    .build()
            );
    }
}
