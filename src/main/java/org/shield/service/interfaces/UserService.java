package org.shield.service.interfaces;

import org.shield.entities.UserBlockchain;

public interface UserService {
    String saveUser(UserBlockchain user);
    boolean updatePassword(String username, String password);
}
