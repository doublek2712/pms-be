package com.douk.PMS.service;

import com.douk.PMS.dto.DailyTkDTO;
import com.douk.PMS.entity.DailyTk;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.TkType;

import java.time.LocalDate;
import java.util.List;

public interface DailyTkService {

    List<DailyTk> getAllDailyTk();

    List<DailyTk> getByDay(LocalDate day);

    void addDailyTk(DailyTkDTO dailyTkDTO);

    void updateDailyTkType(LocalDate day, Employee employee, TkType type);

    String addMultiDailyTk(List<DailyTkDTO> dailyTkDTO);
}
