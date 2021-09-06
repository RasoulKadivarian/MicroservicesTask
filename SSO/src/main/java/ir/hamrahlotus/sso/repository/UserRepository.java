package ir.hamrahlotus.sso.repository;

import ir.hamrahlotus.sso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    boolean existsByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);

    void deleteByUsername(String username);
}
