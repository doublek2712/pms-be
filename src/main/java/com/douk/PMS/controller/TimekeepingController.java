package com.douk.PMS.controller;

import com.douk.PMS.dto.TimekeepingDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Timekeeping;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.service.EmployeeService;
import com.douk.PMS.service.TimekeepingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/timekeeping")
public class TimekeepingController {

    @Autowired
    private TimekeepingService timekeepingService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(path = "save")
    public String addTimekeeping(@RequestBody TimekeepingDTO timekeepingDTO){
        timekeepingService.addTimekeeping(timekeepingDTO);
        return "success";
    }

    @GetMapping(path = "all")
    public List<Timekeeping> getAllTimekeeping(
            @RequestParam(name = "month", required = false) YearMonth month,
            @RequestParam(name = "employee", required = false) Long employeeId
    ){
        if(month != null && employeeId != null){
            Optional<Employee> timekeeping = employeeRepository.findById(employeeId);
            if(timekeeping.isPresent())
                return (List<Timekeeping>) timekeepingService.getTimekeepingByMonthAndEmployee(month, timekeeping.get());
        }
        if(month != null){
            return timekeepingService.getAllTimekeepingByMonth(month);
        }
        if(employeeId != null){
            Optional<Employee> timekeeping = employeeRepository.findById(employeeId);
            if(timekeeping.isPresent())
                return timekeepingService.getAllTimekeepingByEmployee(timekeeping.get());
            else
                throw new IllegalStateException("employee id not exist");
        }
        return timekeepingService.getAllTimekeeping();
    }

    @DeleteMapping(path = "delete")
    public String deleteTimekeeping(
            @RequestParam(name = "month") YearMonth month,
            @RequestParam(name = "employee") Long employeeId){

        Optional<Employee> timekeeping = employeeRepository.findById(employeeId);
        if(timekeeping.isPresent())
            return timekeepingService.deleteTimekeeping(month, timekeeping.get());

        return "timekeeping not exist";
    }


}
