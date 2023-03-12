package com.smwu.donedone.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DoneDoneException extends RuntimeException {

    private HttpStatus status;

    public DoneDoneException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
    }
}
