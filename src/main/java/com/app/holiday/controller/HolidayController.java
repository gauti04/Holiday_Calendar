package com.app.holiday.controller;

import com.app.holiday.dto.HolidayRequest;
import com.app.holiday.dto.HolidayResponse;
import com.app.holiday.dto.PageResponse;
import com.app.holiday.service.HolidayService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    private final HolidayService service;

    private static final Logger log = LoggerFactory.getLogger(HolidayController.class);

    public HolidayController(HolidayService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HolidayResponse> createHoliday(
            @Valid @RequestBody HolidayRequest request) {
        log.info("Adding a new Holiday {}", request);

        return ResponseEntity.ok(service.createHoliday(request));
    }

    @GetMapping
    public ResponseEntity<?> getHolidays(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer year,
            Pageable pageable) {

        // If a specific date filter is provided
        if (date != null) {
            if (pageable == null || pageable.isUnpaged()) {
                List<HolidayResponse> list = service.getHolidaysByDate(LocalDate.parse(date));
                return ResponseEntity.ok(list);
            }
            Page<HolidayResponse> page = service.getHolidaysByDate(LocalDate.parse(date), pageable);
            return ResponseEntity.ok(PageResponse.from(page));
        }

        // If a specific year filter is provided
        if (year != null) {
            if (pageable == null || pageable.isUnpaged()) {
                List<HolidayResponse> list = service.getHolidaysByYear(year);
                return ResponseEntity.ok(list);
            }
            Page<HolidayResponse> page = service.getHolidaysByYear(year, pageable);
            return ResponseEntity.ok(page);
        }

        // No filters
        if (pageable == null || pageable.isUnpaged()) {
            List<HolidayResponse> list = service.getAllHolidays();
            return ResponseEntity.ok(list);
        }

        Page<HolidayResponse> page = service.getAllHolidays(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        service.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }
}