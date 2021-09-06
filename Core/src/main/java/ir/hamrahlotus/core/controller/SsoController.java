package ir.hamrahlotus.core.controller;

import ir.hamrahlotus.core.feignclients.SSOClient;
import ir.hamrahlotus.core.model.CreateUserRequestBody;
import ir.hamrahlotus.core.security.jwt.AuthenticateRequest;
import ir.hamrahlotus.core.security.jwt.AuthenticateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/core/users")
public class SsoController {

    private final SSOClient ssoClient;

    @PostMapping
    public ResponseEntity<AuthenticateResponse> create(@RequestBody CreateUserRequestBody user) {
        return ResponseEntity.ok(ssoClient.newUser(user));
    }

    @PostMapping("/token")
    public ResponseEntity<AuthenticateResponse> generateToken(@RequestBody AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(ssoClient.generateToken(authenticateRequest));
    }

}
