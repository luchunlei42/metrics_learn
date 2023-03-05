package com.chunlei.notification.service.Impl;

import com.chunlei.notification.domain.NotificationType;
import com.chunlei.notification.domain.Recipient;
import com.chunlei.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.text.MessageFormat;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;
    @Override
    public void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException {
        final String subject = env.getProperty(type.getSubject());
        final String text = MessageFormat.format(env.getProperty(type.getText()),recipient.getAccountName());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo(recipient.getEmail());
        helper.setSubject(subject);
        helper.setText(text);
        if(StringUtils.hasLength(attachment)){
            helper.addAttachment(env.getProperty(type.getAttachment()),new ByteArrayResource(attachment.getBytes()));
        }
        mailSender.send(message);
    }
}
