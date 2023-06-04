package com.douk.PMS.dto;

import java.time.YearMonth;

public class TimekeepingDTO {

    private YearMonth month;
    private int working_days;
    private int days_off;
    private int overtime;
    private int total;
    private Long staffId;

    public TimekeepingDTO() {
    }

    public TimekeepingDTO(YearMonth month, int working_days, int days_off, int overtime, int total, Long staffId) {
        this.month = month;
        this.working_days = working_days;
        this.days_off = days_off;
        this.overtime = overtime;
        this.total = total;
        this.staffId = staffId;
    }

    public TimekeepingDTO(YearMonth month, Long staffId) {
        this.month = month;
        this.staffId = staffId;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public int getWorking_days() {
        return working_days;
    }

    public void setWorking_days(int working_days) {
        this.working_days = working_days;
    }

    public int getDays_off() {
        return days_off;
    }

    public void setDays_off(int days_off) {
        this.days_off = days_off;
    }

    public int getOvertime() {
        return overtime;
    }

    public void setOvertime(int overtime) {
        this.overtime = overtime;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
