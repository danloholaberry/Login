package com.bci.login.validation;


import com.bci.login.exception.InvalidPasswordException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN = "^(?=^[a-zA-Z\\d]{8,12}$)(?=[^A-Z]*[A-Z][^A-Z]*$)(?=[^\\d]*\\d[^\\d]*\\d[^\\d]*$)[a-zA-Z\\d]*$";

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        Boolean result = password != null && password.matches(PASSWORD_PATTERN);
        if (!result) {
         throw new InvalidPasswordException("Verificar Password. Debe contener solo una Mayúscula, dos números y minúsculas. Máximo 12 - Mínino 8");
        }
        return result;
    }
}
