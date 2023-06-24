package com.douk.PMS.service;

import com.douk.PMS.dto.RequestDTO;
import com.douk.PMS.entity.Request;
import com.douk.PMS.payload.response.RequestPayrollResponse;

import java.util.List;

public interface RequestService {

    void addRequest(RequestDTO requestDTO);

    void deleteRequest(Long id);

    List<Request> getAllRequest();

    RequestPayrollResponse sendRequest(String toEmail);

    Boolean verifyEmail(String email);
}
