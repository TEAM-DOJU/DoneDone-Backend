package com.smwu.donedone.done.domain;

import com.smwu.donedone.done.domain.exception.InvalidStatusException;
import java.util.Arrays;

public enum Status {

    DONE,
    NOT_DONE,
    EMPTY;

    public static Status of(final Status status){
         return Arrays.stream(values())
                .filter(it -> status.name().equals(it.name()))
                .findFirst()
                .orElseThrow(() -> new InvalidStatusException("지원하지 않는 상태입니다 Status = " + status.name()));
    }
}
