package org.shield.service.Impl;

import lombok.AllArgsConstructor;
import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.shield.service.GenerateId;
import org.shield.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(UserBlockchain user) {
        user.setRole("USER");
        List<UserBlockchain> listExistingUser =
                userRepository.findByUsernameAndAndRole(user.getUsername(), user.getRole());
        if (listExistingUser.isEmpty()){
            GenerateId<UserBlockchain> generateId = new GenerateId<>();
            Long id = generateId.getId(userRepository);
            user.setId(id);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
