package com.douk.PMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private String educational_lvl;
    private String birthplace;
    private String ethnicity;
    private String citizenId;
    private LocalDate birthdate;
    private String address;
    private String hometown;
    private String phoneNumber;
    private Long dept;
    private String position;
    private LocalDate startDate;
    private LocalDate contractDate;
    private Double salaryGrade;
    private Long resignationDecision;



    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducational_lvl() {
        return educational_lvl;
    }

    public void setEducational_lvl(String educational_lvl) {
        this.educational_lvl = educational_lvl;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getDept() {
        return dept;
    }

    public void setDept(Long dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public Double getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(Double salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public Long getResignationDecision() {
        return resignationDecision;
    }

    public void setResignationDecision(Long resignationDecision) {
        this.resignationDecision = resignationDecision;
    }

}
