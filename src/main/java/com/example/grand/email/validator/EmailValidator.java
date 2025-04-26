package com.example.grand.email.validator;

import com.example.grand.utils.EmailUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return isValid(email);
    }

    public static boolean isValid(String email) {
        return StringUtils.isBlank(email) || EmailUtils.isValid(email);
    }

}
