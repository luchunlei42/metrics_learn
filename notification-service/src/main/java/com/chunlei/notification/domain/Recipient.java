package com.chunlei.notification.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "recipients")
public class Recipient {
    @Id
    private String accountName;
    private String email;
    private Map<NotificationType,NotificationSettings> schedduledNotifications;
}
