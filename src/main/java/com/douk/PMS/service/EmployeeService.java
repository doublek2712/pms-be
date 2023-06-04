package com.douk.PMS.service;

import com.douk.PMS.dto.EmployeeDTO;
import com.douk.PMS.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployee();

    String addEmployee(EmployeeDTO employeeDTO);

    void updateEmployee(Long id, EmployeeDTO employeeDTO);

    void attachResignationDecision(Long id, Long resignationDecision);

    String deleteEmployee(Long id);

    Optional<Employee> getEmployee(Long id);

    String addEmployeeFromCandidate(EmployeeDTO employeeDTO);

    List<Employee> getAllByName(String name);
}
