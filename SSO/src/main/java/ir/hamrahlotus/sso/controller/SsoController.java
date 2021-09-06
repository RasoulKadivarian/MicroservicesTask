package ir.hamrahlotus.sso.controller;

import ir.hamrahlotus.sso.exception.UserNotFoundException;
import ir.hamrahlotus.sso.model.CreateUserRequestBody;
import ir.hamrahlotus.sso.model.User;
import ir.hamrahlotus.sso.security.UserDetailsServiceImpl;
import ir.hamrahlotus.sso.security.jwt.AuthenticateRequest;
import ir.hamrahlotus.sso.security.jwt.AuthenticateResponse;
import ir.hamrahlotus.sso.security.jwt.JwtUtil;
import ir.hamrahlotus.sso.service.AuthService;
import ir.hamrahlotus.sso.service.UserService;
import ir.hamrahlotus.sso.specifications.DynamicFilter;
import ir.hamrahlotus.sso.specifications.GenericSpecification;
import ir.hamrahlotus.sso.specifications.QueryOperator;
import ir.hamrahlotus.sso.utils.UserConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sso")
public class SsoController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/token")
    public AuthenticateResponse generateAccessToken(@RequestBody AuthenticateRequest authenticateRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Username or Password not valid!\n" + e);
        }
        return authService.generateToken(authenticateRequest);
    }

    @PostMapping
    public AuthenticateResponse create(@Valid @RequestBody CreateUserRequestBody user) {
        User addedUser = null;
        addedUser = userService.add(UserConvertor.convert(user));
        if (addedUser == null) {
            throw new RuntimeException("the user is already!");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthenticateResponse(token);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> aUser(@PathVariable("username") String username) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found by username: " + username));
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<List<User>> users(@RequestParam Map<String, Object> params) {
        List<User> userList;
        GenericSpecification<User> specification = generateSpecification(params);
        userList = userService.find(specification);
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> removeUser(@PathVariable("username") String username) {
        userService.remove(username);
        return ResponseEntity.noContent().build();
    }

    private GenericSpecification<User> generateSpecification(@RequestParam Map<String, Object> params) {
        GenericSpecification<User> specification = new GenericSpecification<>();
        params.keySet().stream().filter(s -> s != null && !s.isEmpty()).forEach(s -> {
            specification.addFilter(DynamicFilter.builder()
                    .field(s).value(params.get(s))
                    .queryOperator(QueryOperator.EQUAL).build());
        });
        return specification;
    }
}
