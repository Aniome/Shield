package org.shield.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface ValidateUsername {
    String message() default "Такой пользователь уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
