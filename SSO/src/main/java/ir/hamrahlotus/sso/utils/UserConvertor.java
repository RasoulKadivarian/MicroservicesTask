package ir.hamrahlotus.sso.utils;

import ir.hamrahlotus.sso.model.CreateUserRequestBody;
import ir.hamrahlotus.sso.model.Role;
import ir.hamrahlotus.sso.model.User;
import ir.hamrahlotus.sso.model.Wallet;

public class UserConvertor {
    private UserConvertor() {
    }

    public static User convert(CreateUserRequestBody requestBody) {
        User user = new User();
        user.setPassword(requestBody.getPassword());
        Role role=new Role();
        role.setName(requestBody.getRoleName());
        user.setRole(role);
        user.setWallet(new Wallet(requestBody.getWalletBalance()));
        user.setUsername(requestBody.getUsername());
        return user;
    }
}
