package br.com.senac.api.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("spring.mail.username")
    private String emailFrom;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String emailTo, String emailTitle, String emailMessage) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(emailFrom);
        email.setTo(emailTo);
        email.setSubject(emailTitle);
        email.setText(emailMessage);

        javaMailSender.send(email);
    }
}
