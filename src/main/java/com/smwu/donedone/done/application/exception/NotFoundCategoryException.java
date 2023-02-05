package com.smwu.donedone.done.application.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundCategoryException extends DoneDoneException {
    public NotFoundCategoryException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
