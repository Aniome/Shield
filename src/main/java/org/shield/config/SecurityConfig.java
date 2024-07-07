package org.shield.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/profile").hasRole("USER")
                        .requestMatchers("/profile/chain").hasRole("USER")
                        .requestMatchers("/profile/create").hasRole("USER")
                        .requestMatchers("/api/mine").hasRole("USER")
                        .requestMatchers("/api/new-user").permitAll()
                        .requestMatchers("/api/chain").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                                .requestMatchers("/resources/**", "/static/**").permitAll()
                        //.requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**").permitAll()
                )
                .formLogin(form -> form.loginPage("/").permitAll().defaultSuccessUrl("/profile", true));
        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEventPublisher.class)
    DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
        return new DefaultAuthenticationEventPublisher(delegate);
    }
}
