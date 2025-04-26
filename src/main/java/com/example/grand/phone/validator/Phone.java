package com.example.grand.phone.validator;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
@ReportAsSingleViolation
@JacksonAnnotationsInside
public @interface Phone {

	String message() default "Bad Phone";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
