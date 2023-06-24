package com.douk.PMS.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class DailyTk {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate day;

    private LocalDateTime checkIn;

    private LocalDateTime checkOut;

    private TkType type;

    @PrePersist
    @PreUpdate
    private void generateType() {
        if(day.getDayOfWeek().toString().equals("SUNDAY"))
            type = TkType.T;
        else
            type = TkType.D;
    }

    @ManyToOne
    @JoinColumn(name = "tk_id", referencedColumnName = "id")
    private Timekeeping timekeeping;

    public DailyTk() {
    }

    public DailyTk(LocalDate day, LocalDateTime checkIn, LocalDateTime checkOut, Timekeeping timekeeping) {
        this.day = day;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.timekeeping = timekeeping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TkType getType() {
        return type;
    }

    public void setType(TkType type) {
        this.type = type;
    }

    public Timekeeping getTimekeeping() {
        return timekeeping;
    }

    public void setTimekeeping(Timekeeping timekeeping) {
        this.timekeeping = timekeeping;
    }
}
