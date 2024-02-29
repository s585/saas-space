package name.svetov.posttimeline.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import name.svetov.posttimeline.dto.PostTimelineRq;
import name.svetov.posttimeline.dto.PostTimelineRs;
import org.reactivestreams.Publisher;

import static name.svetov.constants.EndpointConstants.POST_TIMELINE_ENDPOINT;

public interface PostTimelineController {
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Post(POST_TIMELINE_ENDPOINT)
    Publisher<PostTimelineRs> getTimeline(@RequestBody @Body PostTimelineRq rq);
}
