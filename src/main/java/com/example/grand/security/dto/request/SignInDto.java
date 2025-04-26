package com.example.grand.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {

	@NotBlank
	private String email;
	@NotBlank
	private String password;

}
