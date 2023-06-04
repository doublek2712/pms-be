package com.douk.PMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.YearMonth;
import java.util.List;

@Entity
public class Timekeeping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private YearMonth month;
    private int working_days;
    private int days_off;
    private int overtime;
    private int total;

    @ManyToOne
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Employee employee;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timekeeping")
    private List<DailyTk> dailyTkList;

    public Timekeeping() {
    }

    public Timekeeping(YearMonth month, int working_days, int days_off, int overtime, int total, Employee employee) {
        this.month = month;
        this.working_days = working_days;
        this.days_off = days_off;
        this.overtime = overtime;
        this.total = total;
        this.employee = employee;
    }

    public Timekeeping(YearMonth month, Employee employee) {
        this.month = month;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<DailyTk> getDailyTkList() {
        return dailyTkList;
    }

    public void setDailyTkList(List<DailyTk> dailyTkList) {
        this.dailyTkList = dailyTkList;
    }
}
