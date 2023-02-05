package com.smwu.donedone.done.ui.dto;

import com.smwu.donedone.done.application.dto.CreateDoneDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateDoneRequest {

    private String title;
    private Long categoryId;

    public CreateDoneDto toServiceDto() {
        return new CreateDoneDto(title, categoryId);
    }
}
