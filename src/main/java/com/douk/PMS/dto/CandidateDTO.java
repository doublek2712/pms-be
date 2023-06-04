package com.douk.PMS.dto;

import com.douk.PMS.entity.FileStorage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class CandidateDTO {

    private String firstName;
    private String lastName;
    private String applyPosition;

    public CandidateDTO() {
    }

    public CandidateDTO(String firstName, String lastName, String applyPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.applyPosition = applyPosition;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getApplyPosition() {
        return applyPosition;
    }

    public void setApplyPosition(String applyPosition) {
        this.applyPosition = applyPosition;
    }
}
