package com.rasl.Validator;

import com.rasl.pojo.dto.UserDTO;
import com.rasl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for {@link UserDTO} class,
 * implements {@link Validator} interface.
 *
 * @author Aslanov Ruslan
 * @version 1.0
 */

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if(user.getUsername().length() < 5 || user.getUsername().length() > 12){
            errors.rejectValue("username", "Size.userForm.username");
        }

        if(userService.userExists(user.getUsername())){
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (userService.userWithEmailExists(user.getEmail())){
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
