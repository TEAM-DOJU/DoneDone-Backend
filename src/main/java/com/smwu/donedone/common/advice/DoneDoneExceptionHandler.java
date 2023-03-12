package com.smwu.donedone.common.advice;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DoneDoneExceptionHandler {

    @ExceptionHandler(DoneDoneException.class)
    public ResponseEntity<String> NotFoundMemberExceptionHandle(DoneDoneException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
