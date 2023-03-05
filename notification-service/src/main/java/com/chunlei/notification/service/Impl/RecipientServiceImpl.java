package com.chunlei.notification.service.Impl;

import com.chunlei.notification.domain.NotificationType;
import com.chunlei.notification.domain.Recipient;
import com.chunlei.notification.repository.RecipientRepository;
import com.chunlei.notification.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecipientServiceImpl implements RecipientService {
    @Autowired
    private RecipientRepository repository;
    @Override
    public Recipient findByAccountName(String accountName) {
        return repository.findByAccountName(accountName);
    }

    @Override
    public List<Recipient> findReadyToNotify(NotificationType type) {
        switch ((type)){
            case BACKUP:
                return repository.findReadyForBackup();
            case REMIND:
                return repository.findReadyForRemind();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Recipient save(String accountName, Recipient recipient) {
        recipient.setAccountName(accountName);
        recipient.getSchedduledNotifications().values()
                .forEach(settings->{
                    if(settings.getLastNotified() == null){
                        settings.setLastNotified(new Date());
                    }
                });
        repository.save(recipient);
        return recipient;
    }

    @Override
    public void markNotified(NotificationType type, Recipient recipient) {
        recipient.getSchedduledNotifications().get(type).setLastNotified(new Date());
        repository.save(recipient);
    }
}
