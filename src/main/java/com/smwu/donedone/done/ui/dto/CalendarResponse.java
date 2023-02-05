package com.smwu.donedone.done.ui.dto;

import com.smwu.donedone.done.application.dto.CalendarDto;
import com.smwu.donedone.done.domain.Status;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CalendarResponse {

    private List<String> calendarStatuses;

    public static CalendarResponse from(CalendarDto calendarDto) {
        return new CalendarResponse(
                calendarDto.getCalendarStatuses().stream()
                .map(Status::of)
                .map(Enum::name)
                .collect(Collectors.toList())
        );
    }
}
