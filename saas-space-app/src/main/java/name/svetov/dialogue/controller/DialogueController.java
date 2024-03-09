package name.svetov.dialogue.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import name.svetov.dialogue.dto.DialogueRq;
import name.svetov.dialogue.dto.DialogueRs;
import org.reactivestreams.Publisher;

import java.util.UUID;

import static name.svetov.constants.EndpointConstants.CREATE_DIALOGUE_ENDPOINT;
import static name.svetov.constants.EndpointConstants.GET_DIALOGUE_ENDPOINT;

public interface DialogueController {
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Get(GET_DIALOGUE_ENDPOINT + "/{dialogueId}")
    Publisher<DialogueRs> get(@PathVariable("dialogueId") UUID dialogueId);

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Post(CREATE_DIALOGUE_ENDPOINT)
    Publisher<DialogueRs> create(@Body @RequestBody DialogueRq dialogue);
}
