package com.smwu.donedone.done.domain.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class InvalidStatusException extends DoneDoneException {
    public InvalidStatusException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
