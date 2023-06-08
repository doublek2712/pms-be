package com.douk.PMS.service;

import com.douk.PMS.dto.EmailDetails;
import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.dto.VerifyTokenDTO;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);

    void sendPassword(UserDTO user, String password);

}
