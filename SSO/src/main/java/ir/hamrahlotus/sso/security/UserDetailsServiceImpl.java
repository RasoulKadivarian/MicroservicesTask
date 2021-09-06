package ir.hamrahlotus.sso.security;

import ir.hamrahlotus.sso.model.User;
import ir.hamrahlotus.sso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(user_name)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found, user name:" + user_name));

        return new UserDetailsImpl(user);
    }
}
