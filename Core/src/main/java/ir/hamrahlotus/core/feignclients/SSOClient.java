package ir.hamrahlotus.core.feignclients;

import ir.hamrahlotus.core.model.CreateUserRequestBody;
import ir.hamrahlotus.core.security.jwt.AuthenticateRequest;
import ir.hamrahlotus.core.security.jwt.AuthenticateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("SSO")
public interface SSOClient {

    @PostMapping("/api/sso")
    AuthenticateResponse newUser(@RequestBody CreateUserRequestBody createUserRequestBody);

    @PostMapping("/api/sso/token")
    AuthenticateResponse generateToken(@RequestBody AuthenticateRequest authenticateRequest);
}
