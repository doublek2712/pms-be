package com.douk.PMS.controller;

import com.douk.PMS.dto.EmployeeDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.User;
import com.douk.PMS.repo.UserRepository;
import com.douk.PMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/employee")
//@PreAuthorize("hasAnyAuthority('HR', 'ACCOUNTANT')")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping(path = "all")
    public List<Employee> getAllEmployee(
            @RequestParam(name = "name", required = false) String name
    ){
        return employeeService.getAllByName(name);
    }

    @GetMapping
    public Employee getEmployee(@RequestParam(name = "id") Long id){
        return employeeService.getEmployee(id).get();
    }


    @PostMapping(path = "save")
    public String addEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
    }

    @PostMapping(path = "save/multi")
    public String addMultiEmployee(@RequestBody List<EmployeeDTO> employeeDTO){
        for(int i=0;i<employeeDTO.size();i++){
            EmployeeDTO item = employeeDTO.get(i);
            employeeService.addEmployee(item);
        }
        return "success add multi employee";
    }

    @PutMapping(path = "update")
    public String updateEmployee(@RequestParam(name = "id") Long id,
                               @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @PutMapping(path = "{employeeId}/attach")
    public void attachResignationDecision(@PathVariable("employeeId") Long id,
                                          @RequestBody Long resignationDecision){
        employeeService.attachResignationDecision(id, resignationDecision);
    }

    @DeleteMapping(path = "delete")
    public String deleteEmployee(@RequestParam(name = "id") Long id){
        return employeeService.deleteEmployee(id);
    }

}
