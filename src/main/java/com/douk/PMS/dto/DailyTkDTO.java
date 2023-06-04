package com.douk.PMS.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTkDTO {

    private LocalDate day;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private Long tk_id;

    public DailyTkDTO() {
    }

    public DailyTkDTO(LocalDate day,  LocalDateTime checkIn, LocalDateTime checkOut, Long tk_id) {
        this.day = day;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tk_id = tk_id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }


    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Long getTk_id() {
        return tk_id;
    }

    public void setTk_id(Long tk_id) {
        this.tk_id = tk_id;
    }
}
