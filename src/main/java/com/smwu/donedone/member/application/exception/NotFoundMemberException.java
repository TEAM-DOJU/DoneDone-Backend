package com.smwu.donedone.member.application.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends DoneDoneException {
    public NotFoundMemberException(final String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
