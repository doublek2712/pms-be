package com.douk.PMS.repo;

import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, Long> {

    Optional<Payslip> findByEmployeeAndMonth(Employee employee, YearMonth month);

    List<Payslip> findAllByMonth(YearMonth month);

    boolean existsByEmployeeAndMonth(Employee employee, YearMonth month);


}
