package com.app.holiday.service;

import com.app.holiday.dto.HolidayRequest;
import com.app.holiday.dto.HolidayResponse;
import com.app.holiday.entity.Holiday;
import com.app.holiday.mapper.HolidayMapper;
import com.app.holiday.repository.HolidayRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository repository;

    public HolidayService(HolidayRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public HolidayResponse createHoliday(HolidayRequest request) {
        Holiday holiday = HolidayMapper.toEntity(request);
        Holiday saved = repository.save(holiday);
        return HolidayMapper.toResponse(saved);
    }

    public List<HolidayResponse> getAllHolidays() {
        return repository.findAllByOrderByDateAsc()
                .stream()
                .map(HolidayMapper::toResponse)
                .toList();
    }

    public List<HolidayResponse> getHolidaysByDate(LocalDate date) {
        return repository.findByDate(date)
                .stream()
                .map(HolidayMapper::toResponse)
                .toList();
    }

    public List<HolidayResponse> getHolidaysByYear(int year) {
        return repository.findByYear(year)
                .stream()
                .map(HolidayMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteHoliday(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Holiday not found with id: " + id);
        }
        repository.deleteById(id);
    }
}