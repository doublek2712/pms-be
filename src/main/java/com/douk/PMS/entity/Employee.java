package com.douk.PMS.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
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
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private Department dept;
    private String position;
    private LocalDate startDate;
    private LocalDate contractDate;
    private Double salaryGrade;
    private Long resignationDecision;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
    private List<Timekeeping> timekeepingList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Payslip> payslipList;



    public Employee() {
    }

    public Employee(String firstName, String lastName, String gender, String educational_lvl, String birthplace, String ethnicity, String citizenId, LocalDate birthdate, String address, String hometown, String phoneNumber, Department dept, String position, LocalDate startDate, LocalDate contractDate, Double salaryGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.educational_lvl = educational_lvl;
        this.birthplace = birthplace;
        this.ethnicity = ethnicity;
        this.citizenId = citizenId;
        this.birthdate = birthdate;
        this.address = address;
        this.hometown = hometown;
        this.phoneNumber = phoneNumber;
        this.dept = dept;
        this.position = position;
        this.startDate = startDate;
        this.contractDate = contractDate;
        this.salaryGrade = salaryGrade;
    }

    public Employee(String firstName, String lastName, String gender, String educational_lvl, String birthplace, String ethnicity, String citizenId, LocalDate birthdate, String address, String hometown, String phoneNumber, String position, LocalDate startDate, LocalDate contractDate, Double salaryGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.educational_lvl = educational_lvl;
        this.birthplace = birthplace;
        this.ethnicity = ethnicity;
        this.citizenId = citizenId;
        this.birthdate = birthdate;
        this.address = address;
        this.hometown = hometown;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.startDate = startDate;
        this.contractDate = contractDate;
        this.salaryGrade = salaryGrade;
    }

    public Employee(String firstName, String lastName, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

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

    public List<Timekeeping> getTimekeepingList() {
        return timekeepingList;
    }

    public void setTimekeepingList(List<Timekeeping> timekeepingList) {
        this.timekeepingList = timekeepingList;
    }

    public List<Payslip> getPayslipList() {
        return payslipList;
    }

    public void setPayslipList(List<Payslip> payslipList) {
        this.payslipList = payslipList;
    }




}
