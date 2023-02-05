package com.smwu.donedone.member.domain.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundInterestException extends DoneDoneException {
    public NotFoundInterestException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
