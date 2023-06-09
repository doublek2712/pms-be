package com.douk.PMS.impl;

import com.douk.PMS.dto.EmailDetails;
import com.douk.PMS.dto.UserDTO;
import com.douk.PMS.dto.VerifyTokenDTO;
import com.douk.PMS.repo.FileStorageRepository;
import com.douk.PMS.service.EmailService;
import com.douk.PMS.service.FileStorageService;
import com.douk.PMS.utils.FileStorageUtils;
import com.douk.PMS.utils.PasswordGenerator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class EmailImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);
    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(environment.getProperty("spring.mail.username"));
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(environment.getProperty("spring.mail.username"));
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());
            FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @Override
    public String sendToBOD(String toEmail, MultipartFile file) throws IOException {
        byte[] fileData = file.getBytes();

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setSubject("Here your payroll!");
            helper.setTo(toEmail);
            helper.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));

            Multipart multipart = new MimeMultipart();

            // Create the text part of the email
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Dear Sir/Madam,"+
                    "\nHere is the payroll according to your request.");
            multipart.addBodyPart(messageBodyPart);

            // Create the attachment part
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(fileData, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            messageBodyPart.setFileName(file.getOriginalFilename());
            multipart.addBodyPart(messageBodyPart);

            // Set the content of the message to multipart
            mimeMessage.setContent(multipart);

            javaMailSender.send(mimeMessage);
            return "Send email successful";
//            return code;
        } catch (MessagingException e){
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    public void sendPassword(UserDTO user , String password) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(buildEmail(user.getName(), password), true);
            helper.setTo(user.getEmail());
            helper.setSubject("Here your new password to access PMS!");
            helper.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e){
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    public String buildEmail(String name, String password) {
        String sb = "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>" +
                "<table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" +
                "<tbody><tr>" +
                "<td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">" +
                "<table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">" +
                "<tbody><tr>" +
                "<td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">" +
                "<table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">" +
                "<tbody><tr>" +
                "<td style=\"padding-left:10px\"></td>" +
                "<td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">" +
                "<span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Your new password for PMS!</span>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">" +
                "<tbody><tr>" +
                "<td width=\"10\" height=\"10\" valign=\"middle\"></td>" +
                "<td>" +
                "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">" +
                "<tbody><tr>" +
                "<td bgcolor=\"#7D8FF1\" width=\"100%\" height=\"20\"></td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "<td width=\"10\" valign=\"middle\" height=\"10\"></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">" +
                "<tbody><tr>" +
                "<td height=\"30\"><br></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"10\" valign=\"middle\"><br></td>" +
                "<td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">" +
                "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Admin of PMS just added you to our system as a user. Here's your password:</p>" +
                "<div style=\"Margin:0 0 20px 0;font-size:29px;font-weight:700;line-height:25px;color:#fff;background-color:#161248;padding:20px;text-align:center\">" + password + "</div>" +
                "<p>See you soon</p>" +
                "</td>" +
                "<td width=\"10\" valign=\"middle\"><br></td>" +
                "</tr>" +
                "<tr>" +
                "<td height=\"30\"><br></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<div class=\"yj6qo\"></div><div class=\"adL\"></div></div>";

        return sb;
    }

    public String buildIdentityEmail(String name, String code) {
        String sb = "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>" +
                "<table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" +
                "<tbody><tr>" +
                "<td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">" +
                "<table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">" +
                "<tbody><tr>" +
                "<td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">" +
                "<table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">" +
                "<tbody><tr>" +
                "<td style=\"padding-left:10px\"></td>" +
                "<td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">" +
                "<span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Your verification code!</span>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">" +
                "<tbody><tr>" +
                "<td width=\"10\" height=\"10\" valign=\"middle\"></td>" +
                "<td>" +
                "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">" +
                "<tbody><tr>" +
                "<td bgcolor=\"#7D8FF1\" width=\"100%\" height=\"20\"></td>" +
                "</tr>" +
                "</tbody></table>" +
                "</td>" +
                "<td width=\"10\" valign=\"middle\" height=\"10\"></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">" +
                "<tbody><tr>" +
                "<td height=\"30\"><br></td>" +
                "</tr>" +
                "<tr>" +
                "<td width=\"10\" valign=\"middle\"><br></td>" +
                "<td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">" +
                "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p>" +
                "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Here your code:</p>" +
                "<div style=\"Margin:0 0 20px 0;font-size:29px;font-weight:700;line-height:25px;color:#fff;background-color:#161248;padding:20px;text-align:center\">" + code + "</div>" +
                "<p>See you soon</p>" +
                "</td>" +
                "<td width=\"10\" valign=\"middle\"><br></td>" +
                "</tr>" +
                "<tr>" +
                "<td height=\"30\"><br></td>" +
                "</tr>" +
                "</tbody></table>" +
                "<div class=\"yj6qo\"></div><div class=\"adL\"></div></div>";

        return sb;
    }


}
