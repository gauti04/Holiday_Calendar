package com.app.holiday.controller;

import com.app.holiday.dto.HolidayRequest;
import com.app.holiday.dto.HolidayResponse;
import com.app.holiday.service.HolidayService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    private final HolidayService service;

    public HolidayController(HolidayService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HolidayResponse> createHoliday(
            @Valid @RequestBody HolidayRequest request) {

        return ResponseEntity.ok(service.createHoliday(request));
    }

    @GetMapping
    public ResponseEntity<List<HolidayResponse>> getHolidays(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Integer year) {

        if (date != null) {
            return ResponseEntity.ok(
                    service.getHolidaysByDate(LocalDate.parse(date))
            );
        }

        if (year != null) {
            return ResponseEntity.ok(
                    service.getHolidaysByYear(year)
            );
        }

        return ResponseEntity.ok(service.getAllHolidays());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        service.deleteHoliday(id);
        return ResponseEntity.noContent().build();
    }
}