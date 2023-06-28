package com.douk.PMS.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RequestService {

    String sendRequest(String toEmail, MultipartFile file) throws IOException;

    Boolean verifyEmail(String email);


}
