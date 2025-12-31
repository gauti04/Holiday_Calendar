package com.app.holiday.service;

import com.app.holiday.dto.HolidayRequest;
import com.app.holiday.dto.HolidayResponse;
import com.app.holiday.entity.Holiday;
import com.app.holiday.mapper.HolidayMapper;
import com.app.holiday.repository.HolidayRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<HolidayResponse> getAllHolidays(Pageable pageable) {
        return repository.findAllByOrderByDateAsc(pageable)
                .map(HolidayMapper::toResponse);
    }

    public List<HolidayResponse> getHolidaysByDate(LocalDate date) {
        return repository.findByDate(date)
                .stream()
                .map(HolidayMapper::toResponse)
                .toList();
    }

    public Page<HolidayResponse> getHolidaysByDate(LocalDate date, Pageable pageable) {
        return repository.findByDate(date, pageable)
                .map(HolidayMapper::toResponse);
    }

    public List<HolidayResponse> getHolidaysByYear(int year) {
        return repository.findByYear(year)
                .stream()
                .map(HolidayMapper::toResponse)
                .toList();
    }

    public Page<HolidayResponse> getHolidaysByYear(int year, Pageable pageable) {
        return repository.findByYear(year, pageable)
                .map(HolidayMapper::toResponse);
    }

    @Transactional
    public void deleteHoliday(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Holiday not found with id: " + id);
        }
        repository.deleteById(id);
    }
}