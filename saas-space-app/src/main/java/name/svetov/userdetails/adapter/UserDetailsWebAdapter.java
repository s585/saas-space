package name.svetov.userdetails.adapter;

import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;

import java.util.List;
import java.util.UUID;

public interface UserDetailsWebAdapter {
    UserDetailsDto getOneById(UUID userDetailsId);

    List<UserDetailsDto> search(SearchUserRq rq);
}
