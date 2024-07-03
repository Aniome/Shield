package org.shield.config;

import org.shield.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class ShieldUserDetails implements UserDetails {
    private final User user;

    public ShieldUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //Указывает истек ли срок действия учетной записи пользователя
    //true не истек, false учетная запись недействительная
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }


    //Указывает заблокирован пользователь или нет
    //true не заблокирован, false заблокирован
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    //Указывает истек ли срок действия пароля
    //true учетные данные действительные, false больше не действительны
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    //Включен пользователь или нет
    //true включен, false не включен
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
