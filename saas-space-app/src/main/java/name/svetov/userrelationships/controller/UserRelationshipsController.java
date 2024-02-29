package name.svetov.userrelationships.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.reactivestreams.Publisher;

import java.util.UUID;

import static name.svetov.constants.EndpointConstants.ADD_FRIEND_ENDPOINT;
import static name.svetov.constants.EndpointConstants.DELETE_FRIEND_ENDPOINT;

public interface UserRelationshipsController {
    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Put(ADD_FRIEND_ENDPOINT + "/{friendId}")
    Publisher<Boolean> addFriend(@PathVariable("friendId") UUID friendId);

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Produces(MediaType.APPLICATION_JSON)
    @Delete(DELETE_FRIEND_ENDPOINT + "/{friendId}")
    Publisher<Boolean> deleteFriend(@PathVariable("friendId") UUID friendId);
}
