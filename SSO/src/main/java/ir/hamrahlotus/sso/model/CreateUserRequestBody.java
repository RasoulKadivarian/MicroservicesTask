package ir.hamrahlotus.sso.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequestBody {
    private String username;
    private String password;
    private String roleName;
    private Long walletBalance;
}
