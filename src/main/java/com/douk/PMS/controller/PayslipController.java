package com.douk.PMS.controller;

import com.douk.PMS.dto.PayslipDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Payslip;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.service.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/payslip")
public class PayslipController {

    @Autowired
    private PayslipService payslipService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(path = "save")
    public String addPayslip(@RequestBody PayslipDTO payslipDTO){
        return payslipService.addPayslip(payslipDTO);
    }

    @GetMapping(path = "all")
    public List<Payslip> getAllPayslip(){
        return payslipService.getAllPayslip();
    }

    @GetMapping
    public Payslip getPayslip(
            @RequestParam(name = "employee") Long employeeId,
            @RequestParam(name = "month")YearMonth month){
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if(!employee.isPresent())
                throw new IllegalStateException("employee not exist");
            return payslipService.getPayslip(employee.get(), month);
    }

    @DeleteMapping(path = "delete")
    public String deletePayslip(@RequestParam(name = "employee") Long employeeId,
                                @RequestParam(name = "month")YearMonth month){
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent())
            return payslipService.deletePayslip(employee.get(), month);
        return "employee id not exist";
    }

}
