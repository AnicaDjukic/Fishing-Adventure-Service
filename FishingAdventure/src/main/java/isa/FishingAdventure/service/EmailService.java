package isa.FishingAdventure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Async
    public void sendEmail(String email, String subject, String text) throws MailException, MessagingException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        mailMessage.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        mailMessage.setFrom(env.getProperty("spring.mail.username"));
        mailMessage.setSubject(subject);
        mailMessage.setContent(text, "text/html");
        javaMailSender.send(mailMessage);

        System.out.println("Email sent!");
    }

}