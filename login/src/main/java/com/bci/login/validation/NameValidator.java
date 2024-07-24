package com.bci.login.validation;

import com.bci.login.exception.InvalidPasswordException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public void initialize(ValidName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Boolean result = name != null && !name.isEmpty();
        if (!result) {
            throw new InvalidPasswordException("Verificar el campo 'name'");
        }
        return result;
    }
}
