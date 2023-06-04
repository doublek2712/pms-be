package com.douk.PMS.repo;

import com.douk.PMS.entity.DailyTk;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Timekeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyTkRepository extends JpaRepository<DailyTk, Long> {

    Optional<DailyTk> findByDayAndTimekeeping(LocalDate day, Timekeeping timekeeping);
    List<DailyTk> findAllByDay(LocalDate day);
    List<DailyTk> findAllByTimekeeping(Timekeeping timekeeping);
}
