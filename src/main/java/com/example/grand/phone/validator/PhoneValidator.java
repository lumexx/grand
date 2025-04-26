package com.example.grand.phone.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String BASIC_PHONE_REGEXP = "^7\\d{10}$";

    public static boolean isValid(String phone) {
        return StringUtils.isBlank(phone) || Pattern.matches(BASIC_PHONE_REGEXP, phone);
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return isValid(phone);
    }

}
