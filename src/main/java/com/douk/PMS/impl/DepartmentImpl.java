package com.douk.PMS.impl;

import com.douk.PMS.dto.DepartmentDTO;
import com.douk.PMS.entity.Department;
import com.douk.PMS.repo.DepartmentRepository;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {

        Department dept;

        if(departmentDTO.getHeadId() == null){
            dept = new Department(
                    departmentDTO.getName());
        }
        else {
            dept = new Department(
                    departmentDTO.getName(),
                    employeeRepository.findById(departmentDTO.getHeadId()).get()
            );
        }
        departmentRepository.save(dept);
    }

    @Override
    public void deleteDepartment(Long id) {
        boolean exist = departmentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("id not exist");
        }

        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDepartment(Long id) {
        return departmentRepository.findById(id);
    }
}
