package com.smwu.donedone.done.ui.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatisticsResponse {
    private Map<String, Integer> categoryCount;
    private Map<String, Integer> statusCount;
    private Double ratio;

    public static StatisticsResponse of(Map<String, Integer> categoryCount, Map<String, Integer> statusCount, Double ratio) {
        return new StatisticsResponse(categoryCount, statusCount, ratio);
    }
}
