package com.example.grand.common.exception;


import com.example.grand.common.ExceptionType;
import com.example.grand.common.exception.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestAPIException.class)
    public ResponseEntity<String> handleRestAPIException(RestAPIException exception) {
        final ExceptionType exceptionType = exception.getCode();

        return new ResponseEntity<>(exceptionType.name(), exception.getCode().getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleValidationException(ConstraintViolationException exception) {
        ExceptionType exceptionType = ExceptionType.INCORRECT_DATA;

        ErrorDto errorDto = ErrorDto.builder()
                .errorCode(exceptionType)
                .build();

        return new ResponseEntity<>(errorDto, exceptionType.getStatus());
    }


}
