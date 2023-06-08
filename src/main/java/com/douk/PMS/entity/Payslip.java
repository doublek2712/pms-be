package com.douk.PMS.entity;

import com.douk.PMS.utils.TaxCalculator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Value;

import java.time.YearMonth;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payslip {


    public static Long basicSalary = 1490000L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Employee employee;
    private YearMonth month;
    private Long salary;
    private Long overtimePay;
    private Long socialInsurance;
    private Long healthInsurance;
    private Long incomeTax;
    private Long allowances = 0L;
    @Transient
    private Long totalSalary;




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
        return incomeTax;
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
