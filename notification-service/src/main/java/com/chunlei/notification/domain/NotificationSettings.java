package com.chunlei.notification.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NotificationSettings {
    private Boolean active;
    private Frequency frequency;
    private Date lastNotified;
}
