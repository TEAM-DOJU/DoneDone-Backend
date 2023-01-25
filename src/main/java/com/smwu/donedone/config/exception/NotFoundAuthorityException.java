package com.smwu.donedone.config.exception;

import com.smwu.donedone.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundAuthorityException extends DoneDoneException {

    public NotFoundAuthorityException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
