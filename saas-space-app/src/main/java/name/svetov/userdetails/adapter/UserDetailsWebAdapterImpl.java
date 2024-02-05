package name.svetov.userdetails.adapter;

import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import name.svetov.userdetails.converter.UserDetailsConverter;
import name.svetov.userdetails.dto.SearchUserRq;
import name.svetov.userdetails.dto.UserDetailsDto;
import name.svetov.userdetails.service.UserDetailsService;

import java.util.List;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class UserDetailsWebAdapterImpl implements UserDetailsWebAdapter {
    private final UserDetailsService userDetailsService;
    private final UserDetailsConverter converter;

    @Override
    public UserDetailsDto getOneById(UUID userDetailsId) {
        return converter.map(userDetailsService.getOneById(userDetailsId));
    }

    @Override
    public List<UserDetailsDto> search(SearchUserRq rq) {
        return converter.map(userDetailsService.search(converter.map(rq)));
    }
}
