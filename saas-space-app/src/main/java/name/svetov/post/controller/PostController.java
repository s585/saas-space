package name.svetov.post.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import name.svetov.post.dto.PostDto;
import org.reactivestreams.Publisher;

import java.util.UUID;

import static name.svetov.constants.EndpointConstants.*;

public interface PostController {
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Get(GET_POST_ENDPOINT + "/{postId}")
    Publisher<PostDto> get(@PathVariable("postId") UUID postId);

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Post(CREATE_POST_ENDPOINT)
    Publisher<PostDto> create(@Body @RequestBody PostDto post);

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Put(UPDATE_POST_ENDPOINT + "/{postId}")
    Publisher<PostDto> update(@PathVariable("postId") UUID postId,
                              @Body @RequestBody PostDto post);

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Delete(DELETE_POST_ENDPOINT + "/{postId}")
    Publisher<UUID> delete(@PathVariable("postId") UUID postId);
}
