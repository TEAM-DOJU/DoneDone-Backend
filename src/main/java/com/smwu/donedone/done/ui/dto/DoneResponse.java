package com.smwu.donedone.done.ui.dto;

import com.smwu.donedone.done.application.dto.DoneDto;
import java.time.LocalDateTime;
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
    private LocalDateTime date;
    private String status;
    private String category;

    public static DoneResponse of(DoneDto done) {
        return new DoneResponse(
                done.getId(),
                done.getTitle(),
                done.getDate(),
                done.getStatus(),
                done.getCategory());
    }
}
