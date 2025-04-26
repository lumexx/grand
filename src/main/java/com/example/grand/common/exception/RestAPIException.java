package com.example.grand.common.exception;

import com.example.grand.common.ExceptionType;
import lombok.Getter;

@Getter
public class RestAPIException extends RuntimeException {

    private final ExceptionType code;

    public RestAPIException(ExceptionType code) {
        this.code = code;
    }

}