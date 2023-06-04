package com.douk.PMS.service;

import com.douk.PMS.dto.RequestDTO;
import com.douk.PMS.entity.Request;

import java.util.List;

public interface RequestService {

    void addRequest(RequestDTO requestDTO);

    void deleteRequest(Long id);

    List<Request> getAllRequest();
}
