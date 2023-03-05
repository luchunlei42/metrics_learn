package com.chunlei.account.service.Impl;

import com.chunlei.account.dao.AccountDao;
import com.chunlei.account.domain.Account;
import com.chunlei.account.domain.User;
import com.chunlei.account.service.AccountService;
import com.chunlei.account.client.StatisticsServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    StatisticsServiceClient client;
    @Override
    public Account findByName(String name) {
        Account account = accountDao.findByName(name);
        //TODO null check
        Long account_id = account.getId();
        account.setExpenses(accountDao.findExpensesByIdAndType(account_id,1));
        account.setIncomes(accountDao.findExpensesByIdAndType(account_id,2));
        account.setSaving(accountDao.findSavingById(account_id));
        return account;
    }

    @Override
    public int saveCanges(String name, Account account) {
        account.setLastSeen(new Date());
        //手动改
        client.updateStatistics(name, account);
        return 1;
    }

    @Override
    public Account create(User user) {
        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new Date());
        account = accountDao.create(account);
        return account;
    }
}
