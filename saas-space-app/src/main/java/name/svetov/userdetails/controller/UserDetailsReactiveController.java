package name.svetov.userdetails.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static name.svetov.constants.EndpointConstants.*;

public interface UserDetailsReactiveController {
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Get(USER_REACTIVE_ENDPOINT + "/{userDetailsId}")
    Mono<UserDetailsDto> getOneById(@PathVariable("userDetailsId") UUID userDetailsId);

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Produces(MediaType.APPLICATION_JSON)
    @Post(SEARCH_USER_REACTIVE_ENDPOINT)
    Flux<UserDetailsDto> search(@Body @RequestBody SearchUserRq rq);
}
