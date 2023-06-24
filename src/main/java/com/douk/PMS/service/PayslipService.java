package com.douk.PMS.service;

import com.douk.PMS.dto.PayslipDTO;
import com.douk.PMS.entity.Employee;
import com.douk.PMS.entity.Payslip;

import java.time.YearMonth;
import java.util.List;

public interface PayslipService {

    String addPayslip(PayslipDTO payslipDTO);

    String deletePayslip(Employee employee, YearMonth month);

    Payslip getPayslip(Employee employee, YearMonth month);

    List<Payslip>  getAllPayslip();
    List<Payslip>  getAllPayslipByMonth(YearMonth month);

    String addMultiPayslip(List<PayslipDTO> payslipDTO);
}
