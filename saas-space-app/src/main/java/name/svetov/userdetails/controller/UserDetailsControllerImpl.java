package name.svetov.userdetails.controller;

import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.adapter.UserDetailsWebAdapter;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserDetailsControllerImpl implements UserDetailsController {
    private final UserDetailsWebAdapter webAdapter;

    @Override
    public UserDetailsDto getOneById(UUID userDetailsId) {
        return webAdapter.getOneById(userDetailsId);
    }

    @Override
    public List<UserDetailsDto> search(SearchUserRq rq) {
        return webAdapter.search(rq);
    }
}
