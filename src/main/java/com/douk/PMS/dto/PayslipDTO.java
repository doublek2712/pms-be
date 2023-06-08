package com.douk.PMS.dto;

import java.time.YearMonth;

public class PayslipDTO {

    private Long staffId;
    private YearMonth month;
    private Long allowances ;

    public PayslipDTO() {
    }

    public PayslipDTO(Long staffId, YearMonth month, Long salary, Long overtimePay, Long allowances) {
        this.staffId = staffId;
        this.month = month;
        this.allowances = allowances;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }


    public Long getAllowances() {
        return allowances;
    }

    public void setAllowances(Long allowances) {
        this.allowances = allowances;
    }
}
