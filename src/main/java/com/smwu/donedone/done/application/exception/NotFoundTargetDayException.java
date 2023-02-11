package com.smwu.donedone.done.application.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundTargetDayException extends DoneDoneException {
    public NotFoundTargetDayException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
