package com.chunlei.notification.controller;

import com.chunlei.notification.domain.Recipient;
import com.chunlei.notification.service.RecipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/recipients")
public class RecipientController {
    @Autowired
    private RecipientService recipientService;

    @GetMapping("/current")
    public Object getCurrentNotificationSettings(Principal principal){
        return recipientService.findByAccountName(principal.getName());
    }

    @PutMapping("/current")
    public Object saveCurrentNotifications(Principal principal, @RequestBody Recipient recipient){
        return recipientService.save(principal.getName(),recipient);
    }

}
