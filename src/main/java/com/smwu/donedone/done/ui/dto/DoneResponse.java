package com.smwu.donedone.done.ui.dto;

import com.smwu.donedone.done.application.dto.DoneDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DoneResponse {

    private Long id;
    private String title;
    private String status;
    private String category;

    public static DoneResponse of(DoneDto done) {
        return new DoneResponse(
                done.getId(),
                done.getTitle(),
                done.getStatus(),
                done.getCategory());
    }
}
