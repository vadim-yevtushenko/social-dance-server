package com.example.socialdanceserver.service;


import javax.mail.internet.InternetAddress;
import java.util.List;

public interface EmailService {

    void sendEmails(List<InternetAddress> toAddresses, String subject, String text);

}
