package com.smwu.donedone.auth.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundAuthorityException extends DoneDoneException {

    public NotFoundAuthorityException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
