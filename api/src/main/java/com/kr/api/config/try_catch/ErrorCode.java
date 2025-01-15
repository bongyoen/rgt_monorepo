package com.kr.api.config.try_catch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "INVALID INPUT VALUE");

    private final HttpStatus status;
    private final String message;
}