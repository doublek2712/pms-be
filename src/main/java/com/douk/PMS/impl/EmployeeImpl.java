package com.douk.PMS.impl;

import com.douk.PMS.dto.EmployeeDTO;
import com.douk.PMS.entity.Department;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.repo.DepartmentRepository;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public String addEmployee(EmployeeDTO employeeDTO){

        Employee newEmployee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .gender(employeeDTO.getGender())
                .educational_lvl(employeeDTO.getEducational_lvl())
                .birthplace(employeeDTO.getBirthplace())
                .ethnicity(employeeDTO.getEthnicity())
                .citizenId(employeeDTO.getCitizenId())
                .birthdate(employeeDTO.getBirthdate())
                .address(employeeDTO.getAddress())
                .hometown(employeeDTO.getHometown())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .position(employeeDTO.getPosition())
                .startDate(employeeDTO.getStartDate())
                .contractDate(employeeDTO.getContractDate())
                .salaryGrade(employeeDTO.getSalaryGrade())
                .build();

        Optional<Department> dept = departmentRepository.findById(employeeDTO.getDept());

        if(dept.isPresent())
            newEmployee.setDept(dept.get());


        employeeRepository.save(newEmployee);
        return "employee " + newEmployee.getFirstName() + " added";
    }

    @Transactional
    @Override
    public String updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "employee with id" + id +"not exist"));
        employee.setAddress(employeeDTO.getAddress());
        employee.setBirthdate(employeeDTO.getBirthdate());
        employee.setBirthplace(employeeDTO.getBirthplace());
        employee.setEthnicity(employeeDTO.getEthnicity());
        employee.setCitizenId(employeeDTO.getCitizenId());

        employee.setEducational_lvl(employeeDTO.getEducational_lvl());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setSalaryGrade(employeeDTO.getSalaryGrade());
        employee.setGender(employeeDTO.getGender());
        employee.setPosition(employeeDTO.getPosition());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setHometown(employeeDTO.getHometown());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setContractDate(employeeDTO.getContractDate());

        Optional<Department> dept = departmentRepository.findById(employeeDTO.getDept());
        if(dept.isPresent())
            employee.setDept(dept.get());

        return "employee "+employee.getName()+" updated";
    }

    @Transactional
    @Override
    public void attachResignationDecision(Long id, Long resignationDecision) {

    }

    @Override
    public String deleteEmployee(Long id){
        boolean exist = employeeRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("employee not exist");
        }

        employeeRepository.deleteById(id);

        return "successfully deleted";
    }

    @Override
    public Optional<Employee> getEmployee(Long id){
        return employeeRepository.findById(id);
    }

    @Override
    public String addEmployeeFromCandidate(EmployeeDTO employeeDTO) {
        Employee newEmployee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .position(employeeDTO.getPosition())
                .gender(employeeDTO.getGender())
                .build();

        employeeRepository.save(newEmployee);
        return "employee " + newEmployee.getFirstName() + " added, from candidate table";
    }

    @Override
    public List<Employee> getAllByName(String name) {
        if(name != null){
            HashSet<Employee> set  = new HashSet<>();
            set.addAll(employeeRepository.findByFirstNameContaining(name));
            set.addAll(employeeRepository.findByLastNameContaining(name));
            return new ArrayList<>(set);
        }

        return employeeRepository.findAll();
    }


}
