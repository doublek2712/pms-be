package com.douk.PMS.impl;

import com.douk.PMS.dto.RequestDTO;
import com.douk.PMS.entity.Request;
import com.douk.PMS.repo.RequestRepository;
import com.douk.PMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;
    @Override
    public void addRequest(RequestDTO requestDTO) {
        Request newRequest = new Request(
                requestDTO.getBodId(),
                requestDTO.getMessage(),
                requestDTO.getMonth()
        );

        requestRepository.save(newRequest);
    }

    @Override
    public void deleteRequest(Long id) {
        boolean exist = requestRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("request not exist");
        }

        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }
}
