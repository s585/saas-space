package name.svetov.userdetails.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import name.svetov.userdetails.dto.UserDetailsDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static name.svetov.constants.EndpointConstants.USER_ENDPOINT;

public interface UserDetailsController {
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Get(USER_ENDPOINT + "/{userDetailsId}")
    Mono<UserDetailsDto> getOneById(@PathVariable("userDetailsId") UUID userDetailsId);
}
