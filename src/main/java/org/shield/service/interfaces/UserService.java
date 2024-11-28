package org.shield.service.interfaces;

import org.shield.dto.JwtAuthenticationDto;
import org.shield.dto.RefreshTokenDto;
import org.shield.dto.UserCredentialsDto;
import org.shield.entities.UserBlockchain;

import javax.naming.AuthenticationException;

public interface UserService {
    JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException;
    JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception;
    String saveUser(UserBlockchain user);
    boolean updatePassword(String username, String password);
}
