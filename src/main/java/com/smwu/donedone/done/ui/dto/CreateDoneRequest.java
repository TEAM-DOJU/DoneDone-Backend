package com.smwu.donedone.done.ui.dto;

import com.smwu.donedone.done.application.dto.CreateDoneDto;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateDoneRequest {

    private String title;
    private LocalDateTime date;
    private Long categoryId;

    public CreateDoneDto toServiceDto() {
        return new CreateDoneDto(title, date, categoryId);
    }
}
