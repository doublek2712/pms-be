package com.douk.PMS.impl;

import com.douk.PMS.dto.PayslipDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Payslip;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.repo.PayslipRepository;
import com.douk.PMS.service.PayslipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class PayslipImpl implements PayslipService {

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String addPayslip(PayslipDTO payslipDTO) {

        boolean exist = payslipRepository.existsByEmployeeAndMonth(
                employeeRepository.findById(payslipDTO.getStaffId()).get(),
                payslipDTO.getMonth()
        );
        if(exist) {
            throw new IllegalStateException("payslip is already exist");
        }

        Payslip newPayslip = new Payslip(
                employeeRepository.findById(payslipDTO.getStaffId()).get(),
                payslipDTO.getMonth(),
                payslipDTO.getSalary(),
                payslipDTO.getOvertimePay(),
                payslipDTO.getSocialInsurance(),
                payslipDTO.getHealthInsurance(),
                payslipDTO.getAllowances()
        );

        payslipRepository.save(newPayslip);
        return "added payslip of " + newPayslip.getEmployee().getFirstName() + ", month "+ newPayslip.getMonth();
    }

    @Override
    public String deletePayslip(Employee employee, YearMonth month) {
        Optional<Payslip> payslip = payslipRepository.findByEmployeeAndMonth(
                employee,
                month
        );

        if(!payslip.isPresent())
            throw new IllegalStateException("payslip not exist");

        payslipRepository.deleteById(payslip.get().getId());
        return "deleted payslip of :" + employee.getFirstName() + ", month" + month;
    }

    @Override
    public Payslip getPayslip(Employee employee, YearMonth month) {
        return payslipRepository.findByEmployeeAndMonth(employee, month).get();
    }

    @Override
    public List<Payslip> getAllPayslip() {
        return payslipRepository.findAll();
    }
}
