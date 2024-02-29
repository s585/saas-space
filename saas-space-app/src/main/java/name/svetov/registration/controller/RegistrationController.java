package name.svetov.registration.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import name.svetov.registration.dto.RegistrationRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import org.reactivestreams.Publisher;

import static name.svetov.constants.EndpointConstants.REGISTRATION_ENDPOINT;

public interface RegistrationController {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Produces(MediaType.APPLICATION_JSON)
    @Post(REGISTRATION_ENDPOINT)
    Publisher<UserDetailsDto> register(@Body @RequestBody RegistrationRq rq);
}
