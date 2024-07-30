package org.shield.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserValidator implements ConstraintValidator<ValidateUsername, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<UserBlockchain> listExistingUser =
                userRepository.findByUsernameAndAndRole(value, "USER");
        return listExistingUser.isEmpty();
    }
}
