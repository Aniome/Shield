package org.shield.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserBlockchain {
    @Id
    private Long id;
    @Size(min = 6, max = 255, message="Имя пользователя должно содержать от 6 до 255 символов")
    private String username;
    @Size(min = 6, max = 255, message="Пароль должно содержать от 6 до 255 символов")
    private String password;
    private String email;
    private String role;
}
