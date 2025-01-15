package com.kr.api.cmmn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object rtnModel;

    public ApiResponse(Boolean success, String message, Object rtnModel) {
        this.success = success;
        this.message = message;
        this.rtnModel = rtnModel;
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}