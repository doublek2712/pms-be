package com.douk.PMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    private Employee headId;

    @JsonIgnore
    @OneToMany(mappedBy = "dept")
    private List<Employee> employeeList;

    public Department(String name, Employee headId) {
        this.name = name;
        this.headId = headId;
    }

    public Department(String name) {
        this.name = name;
    }


    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setHeadId(Employee headId) {
        this.headId = headId;
    }

}
