package com.app.holiday.repository;

import com.app.holiday.entity.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    // Get holidays for a specific date
    List<Holiday> findByDate(LocalDate date);

    // Pageable version
    Page<Holiday> findByDate(LocalDate date, Pageable pageable);

    // Get holidays for a specific year, sorted by date
    @Query("""
           SELECT h FROM Holiday h
           WHERE YEAR(h.date) = :year
           ORDER BY h.date ASC
           """)
    List<Holiday> findByYear(int year);

    // Pageable version
    @Query(value = "SELECT h FROM Holiday h WHERE YEAR(h.date) = :year",
           countQuery = "SELECT count(h) FROM Holiday h WHERE YEAR(h.date) = :year")
    Page<Holiday> findByYear(int year, Pageable pageable);

    // Get all holidays sorted by date
    List<Holiday> findAllByOrderByDateAsc();

    // Pageable version
    Page<Holiday> findAllByOrderByDateAsc(Pageable pageable);
}