package org.shield.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePassword {
    private String oldPassword;
    @Size(min = 6, max = 255, message="Пароль должно содержать от 6 до 255 символов")
    private String newPassword;
    @Size(min = 6, max = 255, message="Пароль должно содержать от 6 до 255 символов")
    private String confirmPassword;
}
