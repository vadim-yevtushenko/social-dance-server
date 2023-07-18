package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.EmailDto;
import com.example.socialdanceserver.service.AdminService;
import com.example.socialdanceserver.service.EmailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import java.util.List;

@Service
public class AdminServiceImpl extends BaseService implements AdminService {

    @Autowired
    private EmailService emailService;

    @SneakyThrows
    @Override
    public void receiveSupportEmail(EmailDto email) {
        emailService.sendEmails(List.of(new InternetAddress("vadik.chicoto@gmail.com")), "Support message", createMessage(email.getEmail(), email.getFullName(), email.getMessage()));
    }

    private String createMessage(String email, String fullName, String message) {
        String formattedMessage = message.replaceAll("\n", "<br>");
        return String.format("<br>Message from %s<br>Email for answer: %s<br><br>%s", fullName, email, formattedMessage);
    }
}
