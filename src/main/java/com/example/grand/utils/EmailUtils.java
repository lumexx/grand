package com.example.grand.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmailUtils {

	private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

	public static boolean isValid(final String email) {
		return EMAIL_VALIDATOR.isValid(email);
	}

}