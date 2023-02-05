package com.smwu.donedone.done.application.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundDoneException extends DoneDoneException {
    public NotFoundDoneException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
