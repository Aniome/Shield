package org.shield.service.Impl;

import lombok.AllArgsConstructor;
import org.shield.dto.JwtAuthenticationDto;
import org.shield.dto.RefreshTokenDto;
import org.shield.dto.UserCredentialsDto;
import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.shield.security.JwtService;
import org.shield.service.GenerateId;
import org.shield.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        UserBlockchain userBlockchain = findByCredentials(userCredentialsDto);
        return jwtService.generateJwtAuthToken(userBlockchain.getUsername());
    }

    @Override
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtService.validateJwtToken(refreshToken)) {
            Optional<UserBlockchain> optionalUserBlockchain = userRepository.
                    findByUsername(jwtService.getUsernameFromToken(refreshToken));
            if (optionalUserBlockchain.isPresent()) {
                UserBlockchain userBlockchain = optionalUserBlockchain.get();
                return jwtService.refreshBaseToken(userBlockchain.getUsername(), refreshToken);
            }
        }
        throw new AuthenticationException("Invalid refresh token");
    }

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

    //@Transactional(value = "transactionManager", propagation = Propagation.REQUIRES_NEW, rollbackFor = {Throwable.class})
    @Transactional
    @Override
    public boolean updatePassword(String username, String password) {
        Optional<UserBlockchain> user = userRepository.findByUsername(username);
        try {
            if (user.isPresent()) {
                UserBlockchain userBlockchain = user.get();
                userBlockchain.setPassword(passwordEncoder.encode(password));
                userRepository.save(userBlockchain);
                //userRepository.saveAndFlush(userBlockchain);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean verifyPassword(String username, String password) {
        Optional<UserBlockchain> user = userRepository.findByUsername(username);
        return user.filter(userBlockchain ->
                passwordEncoder.matches(password, userBlockchain.getPassword())).isPresent();
    }

    public boolean checkUsername(String username) {
        Optional<UserBlockchain> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    private UserBlockchain findByCredentials(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        Optional<UserBlockchain> optionalUser = userRepository.findByUsername(userCredentialsDto.getUsername());
        if (optionalUser.isPresent()) {
            UserBlockchain user = optionalUser.get();
            if (passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())){
                return user;
            }
        }
        throw new AuthenticationException("Email or password is not correct");
    }
}
