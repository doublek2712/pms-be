package com.douk.PMS.controller;

import com.douk.PMS.dto.DailyTkDTO;
import com.douk.PMS.entity.DailyTk;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.TkType;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.service.DailyTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "api/dailytk")
public class DailyTkController {

    @Autowired
    private DailyTkService dailyTkService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping(path = "save")
    public void addDailyTk(@RequestBody DailyTkDTO dailyTkDTO){
        dailyTkService.addDailyTk(dailyTkDTO);
    }

    @GetMapping(path = "all")
    public List<DailyTk> getAllDailyTk(
            @RequestParam(name = "day", required = false)LocalDate day
    ){
        if(day != null)
            return dailyTkService.getByDay(day);
        return dailyTkService.getAllDailyTk();
    }

    @PutMapping(path = "update")
    public String updateDailyTkType(
            @RequestParam(name = "day") LocalDate day,
            @RequestParam(name = "employeeId") Long employeeId,
            @RequestBody TkType type){

        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if(employee.isPresent()) {
            dailyTkService.updateDailyTkType(day, employee.get(), type);
            return "success";
        }
        throw new IllegalStateException("employee not exist") ;
    }

}
