package com.douk.PMS.dto;

import com.douk.PMS.entity.FileStorage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import org.springframework.web.multipart.MultipartFile;

public class CandidateDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String applyPosition;
    private String cv;

    public CandidateDTO() {
    }

    public CandidateDTO(String firstName, String lastName, String email, String gender, String applyPosition, String cv) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.applyPosition = applyPosition;
        this.cv = cv;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
