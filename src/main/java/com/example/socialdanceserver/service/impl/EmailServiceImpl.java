package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.service.EmailService;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl extends BaseService implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @SneakyThrows
    @Override
    public void sendEmails(List<InternetAddress> toAddresses, String subject, String text) {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, false);

        helper.setSubject(subject);

        InternetAddress fromAddress = new InternetAddress("socialdance214@gmail.com", "Social Dances Webapp");
        helper.setFrom(fromAddress);

        helper.setTo(convertToArray(toAddresses));

        if (text != null) {
            message.setContent(text, "text/html");
//            helper.setText(text, true);
        } else {
            helper.setText("", false);
        }

        emailSender.send(message);
    }

    private InternetAddress[] convertToArray(List<InternetAddress> toAddresses) {
        InternetAddress[] array = new InternetAddress[toAddresses.size()];
        return toAddresses.toArray(array);
    }

}
