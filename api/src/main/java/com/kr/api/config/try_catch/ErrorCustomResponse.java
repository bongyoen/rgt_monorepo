package com.kr.api.config.try_catch;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorCustomResponse {

    private final String code;
    private final String message;
}