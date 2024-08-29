package org.shield.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.shield.entities.UserBlockchain;
import org.shield.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class UserValidator implements ConstraintValidator<ValidateUsername, String> {

    private UserRepository userRepository;

    private static List<UserBlockchain> listExistingUser;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Вернуть true, если значение валидно, false в противном случае
        if (listExistingUser == null) {
            listExistingUser = userRepository.findByUsernameAndAndRole(value, "USER");
        } else {
            boolean valid = listExistingUser.isEmpty();
            listExistingUser = null;
            return valid;
        }
        return listExistingUser.isEmpty();
    }
}
