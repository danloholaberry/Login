package com.bci.login.validation;

import com.bci.login.exception.InvalidEmailException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Boolean result = email != null && email.matches(EMAIL_PATTERN);
        if (!result) {
            throw new InvalidEmailException("Ingresar un mail en formato correcto");
        }
        return result;
    }
}
