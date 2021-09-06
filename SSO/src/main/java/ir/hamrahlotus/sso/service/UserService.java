package ir.hamrahlotus.sso.service;

import ir.hamrahlotus.sso.model.User;
import ir.hamrahlotus.sso.specifications.GenericSpecification;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User add(User user);

    Optional<User> findByUsername(String username);

    void remove(String username);

    List<User> find(GenericSpecification<User> specification);
}
