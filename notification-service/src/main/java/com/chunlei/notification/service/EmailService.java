package com.chunlei.notification.service;

import com.chunlei.notification.domain.NotificationType;
import com.chunlei.notification.domain.Recipient;

import javax.mail.MessagingException;

public interface EmailService {
    void send(NotificationType type, Recipient recipient, String attachment) throws MessagingException;
}
