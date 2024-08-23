package org.shield.service;

import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.shield.userDetails.UserBlockchainUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShieldUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBlockchain> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            UserBlockchain userObj = user.get();
            userObj.setRole("ROLE_" + userObj.getRole());
            return user.map(UserBlockchainUserDetails::new).get();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
