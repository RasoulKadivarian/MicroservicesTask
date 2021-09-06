package ir.hamrahlotus.sso.security.jwt;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticateResponse {
    private String token;
}
