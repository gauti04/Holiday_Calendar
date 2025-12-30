package com.app.holiday.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class HolidayRequest {

    @NotBlank
    private String name;

    @NotNull
    private LocalDate date;

    private String country;

    // getters & setters
}