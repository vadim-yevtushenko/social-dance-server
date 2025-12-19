package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.service.EmailService;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl extends BaseService implements EmailService {

    private JavaMailSender emailSender;

    @Async
    @Override
    public void sendEmails(List<InternetAddress> toAddresses, String subject, String text) {

        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setSubject(subject);

            InternetAddress fromAddress = new InternetAddress("socialdance214@gmail.com", "Social Dances Webapp");
            helper.setFrom(fromAddress);

            helper.setTo(convertToArray(toAddresses));

            if (text != null) {
                message.setContent(text, "text/html; charset=utf-8");
//            helper.setText(text, true);
            } else {
                helper.setText("", false);
            }

            emailSender.send(message);
        } catch (Exception e) {
            log.warn("Email sending failed: {}", e.getMessage());
        }
    }

    private InternetAddress[] convertToArray(List<InternetAddress> toAddresses) {
        InternetAddress[] array = new InternetAddress[toAddresses.size()];
        return toAddresses.toArray(array);
    }

}
