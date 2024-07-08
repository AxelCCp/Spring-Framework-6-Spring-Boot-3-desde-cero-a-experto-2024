package com.andres.curso.springboot.app.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.andres.curso.springboot.app.springbootcrud.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String>{

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        //212 - esto quita un error
        if(this.userService == null){
            return true;
        }
        return !userService.existsByUsername(username);
    }

    @Autowired
    private UserService userService;

}
