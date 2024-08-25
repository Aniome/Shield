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
    private UserRepository userRepository;
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
    public boolean updatePassword(String username, String password) {
        userRepository.updateByUsernameAndAndPassword(username, passwordEncoder.encode(password));
        return true;
//        Optional<UserBlockchain> user = userRepository.findByUsername(username);
//        if (user.isPresent()) {
//            UserBlockchain userBlockchain = user.get();
//            userBlockchain.setPassword(passwordEncoder.encode(password));
//            userBlockchain.setPassword("passwordEncoder.encode(password)");
//            userRepository.save(userBlockchain);
//            return true;
//        }
//        return false;
    }
}
