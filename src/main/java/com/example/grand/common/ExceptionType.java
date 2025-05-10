package com.example.grand.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

	EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST),
	PHONE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST),
	AT_LEAST_ONE_PHONE_SHOULD_EXIST(HttpStatus.BAD_REQUEST),
	AT_LEAST_ONE_EMAIL_SHOULD_EXIST(HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(HttpStatus.BAD_REQUEST),
	EMAIL_DATA_NOT_FOUND(HttpStatus.BAD_REQUEST),
	PHONE_DATA_NOT_FOUND(HttpStatus.BAD_REQUEST),
	JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST),
	TOKEN_EXPIRED(HttpStatus.BAD_REQUEST),
	UNKNOWN_ERROR(HttpStatus.BAD_REQUEST),
	ACCOUNT_NOT_FOUND(HttpStatus.BAD_REQUEST),
	TOKEN_VERIFICATION_FAILED(HttpStatus.BAD_REQUEST),
	NOT_ENOUGH_BALANCE(HttpStatus.BAD_REQUEST),
	SELF_TRANSFER_FORBIDDEN(HttpStatus.BAD_REQUEST),
	NEGATIVE_BALANCE_FORBIDDEN(HttpStatus.BAD_REQUEST),
	INCORRECT_DATA(HttpStatus.BAD_REQUEST);


	private final HttpStatus status;

}
