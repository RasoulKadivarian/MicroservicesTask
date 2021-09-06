package ir.hamrahlotus.sso.service;


import ir.hamrahlotus.sso.security.jwt.AuthenticateRequest;
import ir.hamrahlotus.sso.security.jwt.AuthenticateResponse;

public interface AuthService {
    AuthenticateResponse generateToken(AuthenticateRequest authenticateRequest);
}
