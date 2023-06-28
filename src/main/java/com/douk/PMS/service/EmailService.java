package com.douk.PMS.service;

import com.douk.PMS.dto.EmailDetails;
import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.dto.VerifyTokenDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
    String sendToBOD(String toEmail, MultipartFile file) throws IOException;

    void sendPassword(UserDTO user, String password);

}
