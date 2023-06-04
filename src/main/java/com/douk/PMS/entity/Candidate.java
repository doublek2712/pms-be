package com.douk.PMS.entity;

import jakarta.persistence.*;


@Entity
public class Candidate{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cv", referencedColumnName = "id")
    private FileStorage cv;

    private String applyPosition;

    public Candidate() {
    }

    public Candidate(String firstName, String lastName, String applyPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.applyPosition = applyPosition;
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
}
