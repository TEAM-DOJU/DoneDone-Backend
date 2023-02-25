package com.smwu.donedone.done.domain.service;

import com.smwu.donedone.done.domain.Done;
import com.smwu.donedone.done.domain.Status;
import com.smwu.donedone.done.domain.repository.DoneRepository;
import com.smwu.donedone.done.ui.dto.StatisticsResponse;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final DoneRepository doneRepository;

    public StatisticsResponse getStatistic(int year, int month) {
        final LocalDateTime startDay = LocalDateTime.of(year, Month.of(month), 1, 0, 0, 0)
                .with(TemporalAdjusters.firstDayOfMonth());
        final LocalDateTime lastDay = LocalDateTime.of(year, Month.of(month), 1, 23, 59, 59)
                .with(TemporalAdjusters.lastDayOfMonth());
        final List<Done> monthDone = doneRepository.findByDateBetween(startDay, lastDay);

        Map<String, Integer> categoryCount = new HashMap<>();
        Map<String, Integer> statusCount = new HashMap<>();
        for (Done done : monthDone) {
            categoryCount.merge(done.getCategory().getValue(), 1, Integer::sum);
            statusCount.merge(done.getStatus().name(), 1, Integer::sum);
        }

        if (statusCount.get(Status.DONE.name()) != null || statusCount.get(Status.NOT_DONE.name()) != null){
            double doneCount = Double.valueOf(statusCount.getOrDefault(Status.DONE.name(), 0));
            double notDoneCount = Double.valueOf(statusCount.getOrDefault(Status.NOT_DONE.name(), 0));
            double ratio = doneCount / (doneCount + notDoneCount);
            return StatisticsResponse.of(categoryCount, statusCount, ratio);
        }

        return StatisticsResponse.of(categoryCount, statusCount, (double) 0);
    }
}
