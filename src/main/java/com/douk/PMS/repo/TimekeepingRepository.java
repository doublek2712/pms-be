package com.douk.PMS.repo;

import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Timekeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimekeepingRepository extends JpaRepository<Timekeeping, Long> {
    boolean existsByMonthAndEmployee(YearMonth month, Employee employee);

    Optional<Timekeeping> findByMonthAndEmployee(YearMonth month, Employee employee);

    List<Timekeeping> findAllByMonth(YearMonth month);

    List<Timekeeping> findAllByEmployee(Employee employee);
}
