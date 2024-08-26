package org.shield.service.Impl;

import lombok.AllArgsConstructor;
import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.shield.service.GenerateId;
import org.shield.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveUser(UserBlockchain user) {
        user.setRole("USER");
        List<Long> listId = userRepository.findAllId();
        try {
            Long id = GenerateId.getId(listId);
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "User saved";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    @Override
    public boolean updatePassword(String username, String password) {
        Optional<UserBlockchain> user = userRepository.findByUsername(username);
        try {
            if (user.isPresent()) {
                UserBlockchain userBlockchain = user.get();
                userBlockchain.setEmail("t@test.com");
                //userBlockchain.setPassword(passwordEncoder.encode(password));
                //userBlockchain.setPassword("passwordEncoder.encode(password)");
                //userRepository.saveAndFlush(userBlockchain);
                userRepository.save(userBlockchain);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
