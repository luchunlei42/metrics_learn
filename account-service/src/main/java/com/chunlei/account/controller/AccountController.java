package com.chunlei.account.controller;

import com.chunlei.account.domain.Account;
import com.chunlei.account.domain.CommonResult;
import com.chunlei.account.domain.User;
import com.chunlei.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PreAuthorize("#oath2.hasScope('server')")
    @GetMapping("/{name}")
    public Account getAccountByName(@PathVariable String name){
        return accountService.findByName(name);
    }

    @GetMapping("/current")
    public Account getCurrentAccount(Principal principal){
        return accountService.findByName(principal.getName());
    }

    @PutMapping("/current")
    public void saveCurrentAccount(Principal principal, @RequestBody Account account){
        accountService.saveCanges(principal.getName(),account);
    }
    @PostMapping("/create")
    public Account createNewAccount(@RequestBody User user){
        return accountService.create(user);
    }
}
