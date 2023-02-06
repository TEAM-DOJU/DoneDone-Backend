package com.smwu.donedone.done.application.dto;

import com.smwu.donedone.done.domain.Done;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DoneDto {

    private Long id;
    private String title;
    private LocalDateTime date;
    private String status;
    private String category;

    public static DoneDto of(Done savedDone) {
        return new DoneDto(
                savedDone.getId(),
                savedDone.getTitle(),
                savedDone.getDate(),
                savedDone.getStatus().name(),
                savedDone.getCategory().getValue());
    }
}
