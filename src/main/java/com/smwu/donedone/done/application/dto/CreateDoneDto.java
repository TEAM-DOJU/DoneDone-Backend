package com.smwu.donedone.done.application.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateDoneDto {

    private String title;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime date;
    private Long categoryId;
}
