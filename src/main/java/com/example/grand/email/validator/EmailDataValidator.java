package com.example.grand.email.validator;

import com.example.grand.common.ExceptionType;
import com.example.grand.email.dto.EmailDataDto;
import com.example.grand.common.exception.RestAPIException;
import com.example.grand.email.repository.EmailDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailDataValidator {

	private final EmailDataRepository emailDataRepository;

	public void validateEmailData(EmailDataDto dto) {
		boolean existsByEmail = emailDataRepository.existsByEmail(dto.getEmail());

		if (existsByEmail) {
			throw new RestAPIException(ExceptionType.EMAIL_ALREADY_EXISTS);
		}
	}

}
