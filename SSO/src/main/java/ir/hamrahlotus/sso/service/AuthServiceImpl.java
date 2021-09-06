package ir.hamrahlotus.sso.service;

import ir.hamrahlotus.sso.security.UserDetailsServiceImpl;
import ir.hamrahlotus.sso.security.jwt.AuthenticateRequest;
import ir.hamrahlotus.sso.security.jwt.AuthenticateResponse;
import ir.hamrahlotus.sso.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticateResponse generateToken(AuthenticateRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails);
            return new AuthenticateResponse(token);
        } else {
            throw new RuntimeException("entered information is invalid!");
        }
    }
}
