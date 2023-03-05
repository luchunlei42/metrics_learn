package com.chunlei.notification.service;

import com.chunlei.notification.domain.NotificationType;
import com.chunlei.notification.domain.Recipient;

import java.util.List;

public interface RecipientService {

    Recipient findByAccountName(String accountName);
    //find recipients are ready to be notified
    List<Recipient> findReadyToNotify(NotificationType type);

    Recipient save(String accountName, Recipient recipient);

    void markNotified(NotificationType type, Recipient recipient);
}
