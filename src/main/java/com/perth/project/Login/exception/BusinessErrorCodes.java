package com.perth.project.Login.exception;

import org.springframework.http.HttpStatus;

public enum BusinessErrorCodes {
    NO_CODE(0, HttpStatus.NOT_IMPLEMENTED),
    ACCOUNT_LOCKED(302, HttpStatus.FORBIDDEN),
    BAD_CREDENTIALS(304, HttpStatus.FORBIDDEN),
    BAD_REGISTER(305, HttpStatus.BAD_REQUEST),
    NO_NOTIFICATION(305, HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}