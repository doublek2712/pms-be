package com.douk.PMS.impl;

import com.douk.PMS.dto.RequestDTO;
import com.douk.PMS.entity.Request;
import com.douk.PMS.payload.response.RequestPayrollResponse;
import com.douk.PMS.repo.RequestRepository;
import com.douk.PMS.repo.UserRepository;
import com.douk.PMS.service.EmailService;
import com.douk.PMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private String verifyCode;


    @Override
    public void addRequest(RequestDTO requestDTO) {
        Request newRequest = new Request(
                userRepository.findByEmail(requestDTO.getEmail()).get().getId(),
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

    @Override
    public RequestPayrollResponse sendRequest(String toEmail) {
        boolean exist = userRepository.existsByEmail(toEmail);
        if(!exist)
            throw new IllegalStateException("user not exist");

        RequestPayrollResponse newRes = RequestPayrollResponse.builder()
                .bodId(userRepository.findByEmail(toEmail).get().getId())
                .code(emailService.sendIdentityBOD(toEmail))
                .build();
         return newRes;
    }

    @Override
    public Boolean verifyEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
