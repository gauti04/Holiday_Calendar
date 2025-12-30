package com.app.holiday.repository;

import com.app.holiday.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    // Get holidays for a specific date
    List<Holiday> findByDate(LocalDate date);

    // Get holidays for a specific year, sorted by date
    @Query("""
           SELECT h FROM Holiday h
           WHERE YEAR(h.date) = :year
           ORDER BY h.date ASC
           """)
    List<Holiday> findByYear(int year);

    // Get all holidays sorted by date
    List<Holiday> findAllByOrderByDateAsc();
}