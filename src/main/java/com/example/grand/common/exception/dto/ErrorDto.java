package com.example.grand.common.exception.dto;

import com.example.grand.common.ExceptionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorDto {

	private final String errorCode;

	@Singular
	private final List<String> details;

	public static class ErrorDtoBuilder {
		public ErrorDtoBuilder errorCode(ExceptionType exceptionType) {
			this.errorCode = exceptionType.name();
			return this;
		}
	}
}
