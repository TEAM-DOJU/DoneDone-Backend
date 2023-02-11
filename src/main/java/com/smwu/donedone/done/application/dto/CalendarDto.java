package com.smwu.donedone.done.application.dto;


import com.smwu.donedone.done.domain.Done;
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
public class CalendarDto {

    private List<Status> calendarStatuses;

    public static CalendarDto of(final List<Status> statuses) {
        return new CalendarDto(statuses);
    }
}
