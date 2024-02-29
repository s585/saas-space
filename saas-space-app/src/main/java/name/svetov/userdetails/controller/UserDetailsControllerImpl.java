package name.svetov.userdetails.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.adapter.UserDetailsWebAdapter;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserDetailsControllerImpl implements UserDetailsController {
    private final UserDetailsWebAdapter webAdapter;

    @Override
    public Publisher<UserDetailsDto> getOneById(UUID userDetailsId) {
        return webAdapter.getOneById(userDetailsId);
    }

    @Override
    public Publisher<UserDetailsDto> search(SearchUserRq rq) {
        return webAdapter.search(rq);
    }
}
