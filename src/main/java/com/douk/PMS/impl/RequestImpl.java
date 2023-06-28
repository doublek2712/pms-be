package com.douk.PMS.impl;

import com.douk.PMS.repo.UserRepository;
import com.douk.PMS.service.EmailService;
import com.douk.PMS.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RequestImpl implements RequestService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;



    @Override
    public String sendRequest(String toEmail, MultipartFile file) throws IOException {

        return emailService.sendToBOD(toEmail, file);
    }

    @Override
    public Boolean verifyEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
