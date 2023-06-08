package com.douk.PMS.dto;

import java.time.LocalDate;

public class BaseDTO {

    private long id;
    private String createdBy;
    private LocalDate createdLocalDate;
    private String modifiedBy;
    private LocalDate modifiedLocalDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedLocalDate() {
        return createdLocalDate;
    }

    public void setCreatedLocalDate(LocalDate createdLocalDate) {
        this.createdLocalDate = createdLocalDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDate getModifiedLocalDate() {
        return modifiedLocalDate;
    }

    public void setModifiedLocalDate(LocalDate modifiedLocalDate) {
        this.modifiedLocalDate = modifiedLocalDate;
    }
}
