package com.app.holiday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class HolidayResponse {

    private Long id;
    private String name;
    private LocalDate date;
    private String country;

    // getters & setters
}