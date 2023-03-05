package com.chunlei.notification.domain;

public enum NotificationType {
    BACKUP("backup.eamil.subject","backup.email.text","backup.email.attachment"),
    REMIND("remind.email.subject","remind.email.text",null);
    private String subject;
    private String text;
    private String attachment;

    NotificationType(String subject,String text,String attachment){
        this.attachment = attachment;
        this.subject = subject;
        this.text = text;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
