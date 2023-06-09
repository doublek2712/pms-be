package com.douk.PMS.impl;

import com.douk.PMS.dto.TimekeepingDTO;
import com.douk.PMS.entity.DailyTk;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Timekeeping;
import com.douk.PMS.entity.TkType;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.repo.TimekeepingRepository;
import com.douk.PMS.service.TimekeepingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class TimekeepingImpl implements TimekeepingService {

    @Autowired
    private TimekeepingRepository timekeepingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void addTimekeeping(TimekeepingDTO timekeepingDTO) {
        boolean exist = timekeepingRepository.existsByMonthAndEmployee(
                timekeepingDTO.getMonth(),
                employeeRepository.findById(timekeepingDTO.getStaffId()).get()
        );

        if(exist){
            throw new IllegalStateException("timekeeping is already exist");
        }

        Timekeeping newTimekeeping = new Timekeeping(
                timekeepingDTO.getMonth(),
                employeeRepository.findById(timekeepingDTO.getStaffId()).get()
        );

        timekeepingRepository.save(newTimekeeping);
    }

    public void calWorkingDay(List<DailyTk> dailyTkList, YearMonth month, Timekeeping self) {
        int working_days = 0;
        int days_off = 0;
        int overtime = 0;
        for (int i = 0; i < dailyTkList.size(); i++) {
            DailyTk item = dailyTkList.get(i);
            if(item.getType().equals(TkType.T))
                overtime++;
            else if (item.getType().equals(TkType.D))
                working_days++;
        }
        days_off = month.lengthOfMonth() - dailyTkList.size();
        self.setWorking_days(working_days);
        self.setDays_off(days_off);
        self.setOvertime(overtime);

    }

    @Transactional
    @Override
    public List<Timekeeping> getAllTimekeeping() {
        List<Timekeeping> list = timekeepingRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            Timekeeping item = list.get(i);
            calWorkingDay(item.getDailyTkList(), item.getMonth(), item);
        }
        return timekeepingRepository.findAll();
    }


    @Override
    public Timekeeping getTimekeepingByMonthAndEmployee(YearMonth month, Employee employee) {
        return timekeepingRepository.findByMonthAndEmployee(month, employee).get();
    }

    @Override
    public List<Timekeeping> getAllTimekeepingByMonth(YearMonth month) {
        return timekeepingRepository.findAllByMonth(month);
    }

    @Override
    public List<Timekeeping> getAllTimekeepingByEmployee(Employee employee) {
        return timekeepingRepository.findAllByEmployee(employee);
    }

    @Override
    public String deleteTimekeeping(YearMonth month, Employee employee) {
        Timekeeping timekeeping = timekeepingRepository.findByMonthAndEmployee(month, employee).get();

        timekeepingRepository.deleteById(timekeeping.getId());

        return "success";

    }
}
