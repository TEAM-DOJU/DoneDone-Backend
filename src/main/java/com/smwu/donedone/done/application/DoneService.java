package com.smwu.donedone.done.application;

import com.smwu.donedone.done.application.dto.CalendarDto;
import com.smwu.donedone.done.application.dto.CreateDoneDto;
import com.smwu.donedone.done.application.dto.DoneDto;
import com.smwu.donedone.done.application.exception.NotFoundCategoryException;
import com.smwu.donedone.done.application.exception.NotFoundDoneException;
import com.smwu.donedone.done.domain.Category;
import com.smwu.donedone.done.domain.Done;
import com.smwu.donedone.done.domain.repository.CategoryRepository;
import com.smwu.donedone.done.domain.repository.DoneRepository;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoneService {

    private final DoneRepository doneRepository;
    private final CategoryRepository categoryRepository;

    public CalendarDto getCalendar(final Integer year, final Integer month) {
        final LocalDateTime startDay = LocalDateTime.of(year, Month.of(month), 1, 0, 0, 0).with(TemporalAdjusters.firstDayOfMonth());
        final LocalDateTime lastDay = LocalDateTime.of(year, Month.of(month), 1, 23,59,59).with(TemporalAdjusters.lastDayOfMonth());
        final List<Done> monthDone = doneRepository.findByDateBetween(startDay, lastDay);
        return CalendarDto.of(monthDone);
    }

    public DoneDto createDone(final CreateDoneDto createDoneDto){
        final Category category = categoryRepository.findById(createDoneDto.getCategoryId())
                .orElseThrow(() -> new NotFoundCategoryException(
                        "해당 카테고리를 찾을 수 없습니다 categoryId = " + createDoneDto.getCategoryId()));
        final Done savedDone = doneRepository.save(new Done(createDoneDto.getTitle(), category));
        return DoneDto.of(savedDone);
    }

    public DoneDto getDone(final Long doneId) {
        final Done done = doneRepository.findById(doneId)
                .orElseThrow(() -> new NotFoundDoneException("해당 던을 찾을 수 없습니다 doneId = " + doneId));
        return DoneDto.of(done);
    }
}
