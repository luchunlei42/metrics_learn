package com.chunlei.account.dao;

import com.chunlei.account.domain.Account;
import com.chunlei.account.domain.Item;
import com.chunlei.account.domain.Saving;
import com.chunlei.account.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDao {

    Account findByName(String accountName);

    List<Item> findExpensesByIdAndType(Long id, Integer type);
    Saving findSavingById(Long id);
    int UpdateSavingByName(@Param("name") String name, Account account);
    Account create(Account account);

    int delete(Account account);
    List<Item> insertExpensesAnsIncomes(List<Item> items);

}
