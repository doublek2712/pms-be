package com.douk.PMS.service;

import com.douk.PMS.dto.TimekeepingDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Timekeeping;
import com.douk.PMS.entity.TkType;

import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface TimekeepingService {

    void addTimekeeping(TimekeepingDTO timekeepingDTO);

    List<Timekeeping> getAllTimekeeping();

    Timekeeping getTimekeepingByMonthAndEmployee(YearMonth month, Employee employee);

    List<Timekeeping> getAllTimekeepingByMonth(YearMonth month);

    List<Timekeeping> getAllTimekeepingByEmployee(Employee employee);

    String deleteTimekeeping(YearMonth month, Employee employee);
}
