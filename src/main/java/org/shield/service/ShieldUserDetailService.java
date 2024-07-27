package org.shield.service;

import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShieldUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    public UserDetails currentUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserBlockchain> user = userRepository.findByUsername(username);
        if (!user.isEmpty()) {
            UserBlockchain userObj = user.getFirst();
            List<String> listRoles = user.stream().map(UserBlockchain::getRole).toList();
            String[] arrayRoles = listRoles.toArray(new String[0]);

            currentUser = User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(arrayRoles)
                    .build();
            return currentUser;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
