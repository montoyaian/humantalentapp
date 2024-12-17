package com.perth.project.Login.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {
    @Getter
    private final BusinessErrorCodes errorCode;

    @Getter
    private final String description;

    public BusinessException(BusinessErrorCodes errorCode, String description) {
        super(description);
        this.errorCode = errorCode;
        this.description = description;
    }
}
