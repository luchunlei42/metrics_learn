package com.chunlei.notification.service.Impl;

import com.chunlei.notification.client.AccountServiceClient;
import com.chunlei.notification.domain.NotificationType;
import com.chunlei.notification.domain.Recipient;
import com.chunlei.notification.service.EmailService;
import com.chunlei.notification.service.NotificationService;
import com.chunlei.notification.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private AccountServiceClient client;
    @Autowired
    private RecipientService recipientService;
    @Autowired
    private EmailService emailService;
    @Override
    @Scheduled(cron = "${backup.cron}")
    public void sendBackupNotifications() {
        final NotificationType type = NotificationType.BACKUP;
        List<Recipient> recipients = recipientService.findReadyToNotify(type);
        recipients.forEach(recipient -> CompletableFuture.runAsync(()->{
            try{
                String attachment = client.getAccount(recipient.getAccountName());
                emailService.send(type,recipient,attachment);
                recipientService.markNotified(type,recipient);
            }catch (Throwable t){
                System.out.println("error");
            }
        }));
    }

    @Override
    @Scheduled(cron = "${remind.cron}")
    public void sendRemindNotifications() {
        final NotificationType type = NotificationType.REMIND;

        List<Recipient> recipients = recipientService.findReadyToNotify(type);
        //log.info("found {} recipients for remind notification", recipients.size());

        recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
            try {
                emailService.send(type, recipient, null);
                recipientService.markNotified(type, recipient);
            } catch (Throwable t) {
                //log.error("an error during remind notification for {}", recipient, t);
            }
        }));
    }
}
