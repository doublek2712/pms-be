package com.douk.PMS.dto;

public class DepartmentDTO {
    private String name;
    private Long headId;

    public DepartmentDTO(String name, Long headId) {
        this.name = name;
        this.headId = headId;
    }

    public DepartmentDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeadId() {
        return headId;
    }

    public void setHeadId(Long headId) {
        this.headId = headId;
    }
}
