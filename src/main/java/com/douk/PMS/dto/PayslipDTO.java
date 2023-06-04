package com.douk.PMS.dto;

import java.time.YearMonth;

public class PayslipDTO {

    private Long staffId;
    private YearMonth month;
    private Long salary ;
    private Long overtimePay ;
    private Long socialInsurance ;
    private Long healthInsurance ;
    private Long allowances ;

    public PayslipDTO() {
    }

    public PayslipDTO(Long staffId, YearMonth month, Long salary, Long overtimePay, Long socialInsurance, Long healthInsurance, Long allowances) {
        this.staffId = staffId;
        this.month = month;
        this.salary = salary;
        this.overtimePay = overtimePay;
        this.socialInsurance = socialInsurance;
        this.healthInsurance = healthInsurance;
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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(Long overtimePay) {
        this.overtimePay = overtimePay;
    }

    public Long getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(Long socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Long getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(Long healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public Long getAllowances() {
        return allowances;
    }

    public void setAllowances(Long allowances) {
        this.allowances = allowances;
    }
}
