package com.smwu.donedone.done.ui;

import com.smwu.donedone.done.application.DoneService;
import com.smwu.donedone.done.application.dto.CalendarDto;
import com.smwu.donedone.done.application.dto.DoneDto;
import com.smwu.donedone.done.ui.dto.CalendarResponse;
import com.smwu.donedone.done.ui.dto.CreateDoneRequest;
import com.smwu.donedone.done.ui.dto.DailyResponse;
import com.smwu.donedone.done.ui.dto.DoneResponse;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoneController {

    private final DoneService doneService;

    @Operation(summary = "달별 던 조회 API", description = "달별 상태를 반환합니다.")
    @GetMapping("/calendar")
    public ResponseEntity<CalendarResponse> getCalendar(@RequestParam(value = "year") final Integer year,
                                                        @RequestParam(value = "month") final Integer month) {
        final CalendarDto calendar = doneService.getCalendar(year, month);
        return ResponseEntity.ok(CalendarResponse.from(calendar));
    }

    @Operation(summary = "던 생성 API", description = "던을 생성합니다.")
    @PostMapping("/done")
    public ResponseEntity<DoneResponse> createDone(@RequestBody CreateDoneRequest request) {
        final DoneDto done = doneService.createDone(request.toServiceDto());
        return ResponseEntity.created(URI.create("/done" + done.getId())).body(DoneResponse.of(done));
    }

    @Operation(summary = "던 단건조회 API", description = "요청한 id를 기반으로 던을 조회합니다.")
    @GetMapping("/done/{doneId}")
    public ResponseEntity<DoneResponse> getDone(@PathVariable Long doneId) {
        final DoneDto done = doneService.getDone(doneId);
        return ResponseEntity.ok(DoneResponse.of(done));
    }

    @Operation(summary = "일별 던 조회 API", description = "일별 던 목록을 반환합니다.")
    @GetMapping("/daily")
    public ResponseEntity<DailyResponse> getDones(@RequestParam(value = "year") final Integer year,
                                                  @RequestParam(value = "month") final Integer month,
                                                  @RequestParam(value = "day") final Integer day) {
        final List<DoneDto> dones = doneService.getDones(year, month, day);
        final List<DoneResponse> result = dones.stream()
                .map(DoneResponse::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DailyResponse.of(result));
    }

}
