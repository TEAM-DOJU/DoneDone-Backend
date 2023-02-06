package com.smwu.donedone.done.ui.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyResponse {

    List<DoneResponse> daily;

    public static DailyResponse of(List<DoneResponse> doneResponses){
        return new DailyResponse(doneResponses);
    }
}
