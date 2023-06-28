package com.douk.PMS.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(name = "name")
    private String name;

    @PrePersist
    @PreUpdate
    private void generateName() {
        name = firstName + " " + lastName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "interview_result", referencedColumnName = "id")
    private FileStorage interview_result;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cv", referencedColumnName = "id")
    private FileStorage cv;

    private String applyPosition;

    private String email;
    private String gender;







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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileStorage getCv() {
        return cv;
    }

    public void setCv(FileStorage cv) {
        this.cv = cv;
    }

    public String getApplyPosition() {
        return applyPosition;
    }

    public void setApplyPosition(String applyPosition) {
        this.applyPosition = applyPosition;
    }

    public FileStorage getInterview_result() {
        return interview_result;
    }

    public void setInterview_result(FileStorage interview_result) {
        this.interview_result = interview_result;
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
}
