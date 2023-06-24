package com.douk.PMS.impl;

import com.douk.PMS.dto.PayslipDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Payslip;
import com.douk.PMS.entity.Timekeeping;
import com.douk.PMS.repo.EmployeeRepository;
import com.douk.PMS.repo.PayslipRepository;
import com.douk.PMS.repo.TimekeepingRepository;
import com.douk.PMS.service.PayslipService;
import com.douk.PMS.utils.TaxCalculator;
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

    @Autowired
    private TimekeepingRepository timekeepingRepository;

    @Override
    public String addPayslip(PayslipDTO payslipDTO) {

        boolean exist = payslipRepository.existsByEmployeeAndMonth(
                employeeRepository.findById(payslipDTO.getStaffId()).get(),
                payslipDTO.getMonth()
        );
        if(exist) {
            throw new IllegalStateException("payslip is already exist");
        }

        Payslip newPayslip = Payslip.builder()
                .employee(employeeRepository.findById(payslipDTO.getStaffId()).get())
                .month(payslipDTO.getMonth())
                .allowances(payslipDTO.getAllowances())
                .healthInsurance((long) (Payslip.basicSalary * 0.06))
                .build();

        Optional<Timekeeping> timekeeping = timekeepingRepository.findByMonthAndEmployee(
                newPayslip.getMonth(),
                newPayslip.getEmployee()
        );

        if(timekeeping.isEmpty())
            throw new IllegalStateException("Timekeeping not found");

        // tính lương

        Long dailySalary =
                (long) ((Payslip.basicSalary * newPayslip.getEmployee().getSalaryGrade()) / newPayslip.getMonth().lengthOfMonth());

        newPayslip.setSalary(calSalary(timekeeping.get().getWorking_days(), dailySalary));
        newPayslip.setOvertimePay(calOvertimePay(timekeeping.get().getOvertime(), dailySalary));
        newPayslip.setSocialInsurance((long) (newPayslip.getSalary() * 0.105));
        newPayslip.setIncomeTax(TaxCalculator.calTax(
                newPayslip.getSalary()
                        + newPayslip.getOvertimePay()
                        + newPayslip.getAllowances()
                        - newPayslip.getSocialInsurance()
                        - newPayslip.getHealthInsurance()));

        payslipRepository.save(newPayslip);
        return "added payslip of " + newPayslip.getEmployee().getFirstName() + ", month "+ newPayslip.getMonth();
    }

    private Long calOvertimePay(int overtime, Long dailySalary) {
        return (long) (overtime * 1.5 * dailySalary);
    }

    private Long calSalary(int workingDays, Long dailySalary) {
        return workingDays * dailySalary;
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

    @Override
    public List<Payslip> getAllPayslipByMonth(YearMonth month) {
        return payslipRepository.findAllByMonth(month);
    }

    @Override
    public String addMultiPayslip(List<PayslipDTO> payslipDTO) {
        String response = "";
        for (int i = 0; i < payslipDTO.size(); i++) {
            PayslipDTO item = payslipDTO.get(i);
            response += addPayslip(item) + "/n";
        }
        return response;
    }
}
