package com.douk.PMS.controller;

import com.douk.PMS.dto.DepartmentDTO;
import com.douk.PMS.entity.Department;
import com.douk.PMS.repo.DepartmentRepository;
import com.douk.PMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @GetMapping(path = "all")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping
    public Optional<Department> getDepartment(@RequestParam(name = "id") Long id){
        return departmentService.getDepartment(id);
    }

    @PostMapping(path = "save")
    public void addDepartment(@RequestBody DepartmentDTO departmentDTO){
        departmentService.addDepartment(departmentDTO);
    }

    @DeleteMapping(path = "delete")
    public void deleteDepartment(@RequestParam(name = "id") Long id){
        departmentService.deleteDepartment(id);
    }


}
