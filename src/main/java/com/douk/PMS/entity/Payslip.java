package com.douk.PMS.entity;

import com.douk.PMS.utils.TaxCalculator;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.time.YearMonth;

@Entity
public class Payslip {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Employee employee;
    private YearMonth month;

    private Long salary = 0L;
    private Long overtimePay = 0L;
    private Long socialInsurance = 0L;
    private Long healthInsurance = 0L;

    @Transient
    private Long incomeTax;
    private Long allowances = 0L;

    @Transient
    private Long totalSalary;

    public Payslip() {
    }

    public Payslip(Employee employee, YearMonth month, Long salary, Long overtimePay, Long socialInsurance, Long healthInsurance, Long allowances) {
        this.employee = employee;
        this.month = month;
        this.salary = salary;
        this.overtimePay = overtimePay;
        this.socialInsurance = socialInsurance;
        this.healthInsurance = healthInsurance;
        this.allowances = allowances;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Long getIncomeTax() {
        return TaxCalculator.calTax(salary + overtimePay + allowances - socialInsurance - healthInsurance);
    }

    public void setIncomeTax(Long incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Long getAllowances() {
        return allowances;
    }

    public void setAllowances(Long allowances) {
        this.allowances = allowances;
    }

    public Long getTotalSalary() {
        return (salary + overtimePay + allowances - socialInsurance - healthInsurance) - getIncomeTax();
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }
}
