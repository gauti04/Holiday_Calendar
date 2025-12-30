package com.app.holiday.mapper;

import com.app.holiday.dto.HolidayRequest;
import com.app.holiday.dto.HolidayResponse;
import com.app.holiday.entity.Holiday;

public class HolidayMapper {

    private HolidayMapper() {}

    public static Holiday toEntity(HolidayRequest request) {
        Holiday holiday = new Holiday();
        holiday.setName(request.getName());
        holiday.setDate(request.getDate());
        holiday.setCountry(request.getCountry());
        return holiday;
    }

    public static HolidayResponse toResponse(Holiday entity) {
        HolidayResponse response = new HolidayResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDate(entity.getDate());
        response.setCountry(entity.getCountry());
        return response;
    }
}