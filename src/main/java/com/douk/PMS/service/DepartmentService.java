package com.douk.PMS.service;

import com.douk.PMS.dto.DepartmentDTO;
import com.douk.PMS.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    void addDepartment(DepartmentDTO departmentDTO);
    void deleteDepartment(Long id);

    List<Department> getAllDepartment();

    Optional<Department> getDepartment(Long id);
}
