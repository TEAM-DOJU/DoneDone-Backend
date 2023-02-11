package com.smwu.donedone.done.application;

import com.smwu.donedone.done.application.dto.CalendarDto;
import com.smwu.donedone.done.application.dto.CreateDoneDto;
import com.smwu.donedone.done.application.dto.DoneDto;
import com.smwu.donedone.done.application.exception.NotFoundCategoryException;
import com.smwu.donedone.done.application.exception.NotFoundDoneException;
import com.smwu.donedone.done.application.exception.NotFoundTargetDayException;
import com.smwu.donedone.done.domain.Category;
import com.smwu.donedone.done.domain.Done;
import com.smwu.donedone.done.domain.Status;
import com.smwu.donedone.done.domain.repository.CategoryRepository;
import com.smwu.donedone.done.domain.repository.DoneRepository;
import com.smwu.donedone.done.domain.service.StatisticsService;
import com.smwu.donedone.done.ui.dto.StatisticsResponse;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DoneService {

    private final DoneRepository doneRepository;
    private final CategoryRepository categoryRepository;
    private final StatisticsService statisticsService;

    public CalendarDto getCalendar(final Integer year, final Integer month) {
        final LocalDateTime startDay = LocalDateTime.of(year, Month.of(month), 1, 0, 0, 0)
                .with(TemporalAdjusters.firstDayOfMonth());
        final LocalDateTime lastDay = LocalDateTime.of(year, Month.of(month), 1, 23, 59, 59)
                .with(TemporalAdjusters.lastDayOfMonth());
        final List<Done> monthDone = doneRepository.findByDateBetween(startDay, lastDay);

        List<Status> statuses = new ArrayList<>();
        final Set<Integer> doneDayList = monthDone.stream()
                .map(Done::getDate)
                .map(LocalDateTime::getDayOfMonth)
                .collect(Collectors.toSet());

        for (int i = 1; i < getLastDayInMonth(year, month); i++) {
            if (doneDayList.contains(i)) {
                int targetDay = i;
                final List<Status> DayOfDoneStatus = monthDone.stream()
                        .filter(it -> it.getDate().getDayOfMonth() == targetDay)
                        .map(Done::getStatus)
                        .collect(Collectors.toList());
                if (DayOfDoneStatus.contains(Status.NOT_DONE)){
                    statuses.add(Status.NOT_DONE);
                    continue;
                }
                statuses.add(Status.DONE);
                continue;
            }
            statuses.add(Status.EMPTY);
        }

        return CalendarDto.of(statuses);
    }

    public DoneDto changeStatus(Long doneId){
        final Done done = doneRepository.findById(doneId)
                .orElseThrow(() -> new NotFoundDoneException("해당 던을 찾을 수 없습니다 doneId = " + doneId));
        done.changeStatus();
        return DoneDto.of(done);
    }

    private int getLastDayInMonth(Integer year, Integer month) {
        return YearMonth.of(year, month).atEndOfMonth().getDayOfMonth();
    }

    public DoneDto createDone(final CreateDoneDto createDoneDto) {
        final Category category = categoryRepository.findById(createDoneDto.getCategoryId())
                .orElseThrow(() -> new NotFoundCategoryException(
                        "해당 카테고리를 찾을 수 없습니다 categoryId = " + createDoneDto.getCategoryId()));
        final Done savedDone = doneRepository
                .save(new Done(createDoneDto.getTitle(), createDoneDto.getDate(), category));
        return DoneDto.of(savedDone);
    }

    public DoneDto getDone(final Long doneId) {
        final Done done = doneRepository.findById(doneId)
                .orElseThrow(() -> new NotFoundDoneException("해당 던을 찾을 수 없습니다 doneId = " + doneId));
        return DoneDto.of(done);
    }

    public List<DoneDto> getDones(Integer year, Integer month, Integer day) {
        final LocalDateTime startDay = LocalDateTime.of(year, month, day, 0, 0, 0);
        final LocalDateTime endDay = LocalDateTime.of(year, month, day, 23, 59, 59);
        return doneRepository.findByDateBetween(startDay, endDay).stream()
                .map(DoneDto::of)
                .collect(Collectors.toList());
    }

    public StatisticsResponse getStatistics(Integer year, Integer month) {
        return statisticsService.getStatistic(year, month);
    }
}
