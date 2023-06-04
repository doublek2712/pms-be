package com.douk.PMS.impl;

import com.douk.PMS.dto.DailyTkDTO;
import com.douk.PMS.entity.DailyTk;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Timekeeping;
import com.douk.PMS.entity.TkType;
import com.douk.PMS.repo.DailyTkRepository;
import com.douk.PMS.repo.DepartmentRepository;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.repo.TimekeepingRepository;
import com.douk.PMS.service.DailyTkService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class DailyTkImpl implements DailyTkService {

    @Autowired
    private DailyTkRepository dailyTkRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TimekeepingRepository timekeepingRepository;

    @Override
    public List<DailyTk> getAllDailyTk() {
        return dailyTkRepository.findAll();
    }

    @Override
    public List<DailyTk> getByDay(LocalDate day) {
        return dailyTkRepository.findAllByDay(day);
    }

    @Override
    public void addDailyTk(DailyTkDTO dailyTkDTO) {

        Optional<DailyTk> dailyTk = dailyTkRepository.findByDayAndTimekeeping(
                dailyTkDTO.getDay(),
                timekeepingRepository.findById(dailyTkDTO.getTk_id()).get());

        if(dailyTk.isPresent()){
            throw new IllegalStateException("dailytk already exist");
        }

        DailyTk newDailyTk = new DailyTk(
                dailyTkDTO.getDay(),
                dailyTkDTO.getCheckIn(),
                dailyTkDTO.getCheckOut(),
                timekeepingRepository.findById(dailyTkDTO.getTk_id()).get()
        );

        dailyTkRepository.save(newDailyTk);
    }

    @Transactional
    @Override
    public void updateDailyTkType(LocalDate day, Employee employee, TkType type) {

        YearMonth month = YearMonth.from(day);

        Optional<Timekeeping> timekeeping = timekeepingRepository.findByMonthAndEmployee(month, employee);

        Optional<DailyTk> dailyTk = dailyTkRepository.findByDayAndTimekeeping(
                day,
                timekeeping.get());
        if(!dailyTk.isPresent())
            throw new IllegalStateException("daily_tk not exist");

        dailyTk.get().setType(type);
    }
}
