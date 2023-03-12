package com.smwu.donedone.member.domain.exception;

import com.smwu.donedone.common.exception.DoneDoneException;
import org.springframework.http.HttpStatus;

public class NotFoundMemberException extends DoneDoneException {

    public NotFoundMemberException() {
        super(HttpStatus.NOT_FOUND,"회원을 찾을 수 없습니다");
    }
}
