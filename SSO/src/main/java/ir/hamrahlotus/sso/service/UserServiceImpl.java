package ir.hamrahlotus.sso.service;

import ir.hamrahlotus.sso.model.Role;
import ir.hamrahlotus.sso.model.User;
import ir.hamrahlotus.sso.model.Wallet;
import ir.hamrahlotus.sso.repository.RoleRepository;
import ir.hamrahlotus.sso.repository.UserRepository;
import ir.hamrahlotus.sso.specifications.GenericSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public User add(User user) {
        if (user == null) {
            throw new RuntimeException("user is null!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName(user.getRole().getName())
                .orElseThrow(() -> new RuntimeException("Role not found!"));
        user.setRole(role);
        Wallet wallet = new Wallet();
        user.setWallet(wallet);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    @Transactional
    @Override
    public void remove(String username) {
        userRepository.deleteByUsername(username);
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> find(GenericSpecification<User> specification) {
        return userRepository.findAll(specification);
    }

}
