package com.chunlei.account.service;

import com.chunlei.account.domain.Account;
import com.chunlei.account.domain.User;

public interface AccountService {
    Account findByName(String name);

    int saveCanges(String name, Account account);

    Account create(User user);
}
