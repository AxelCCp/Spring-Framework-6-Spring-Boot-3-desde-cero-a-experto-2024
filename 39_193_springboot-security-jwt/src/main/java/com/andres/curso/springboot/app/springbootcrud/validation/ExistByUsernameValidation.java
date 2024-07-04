package com.andres.curso.springboot.app.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.andres.curso.springboot.app.springbootcrud.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistByUsernameValidation implements ConstraintValidator<ExistByUsername, String>{

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !this.userService.existByUsername(username);
    }

    @Autowired
    private UserService userService;

}
