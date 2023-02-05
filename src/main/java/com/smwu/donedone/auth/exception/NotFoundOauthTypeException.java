package com.smwu.donedone.auth.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundOauthTypeException extends DoneDoneException {
    public NotFoundOauthTypeException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
