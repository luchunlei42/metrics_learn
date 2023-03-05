package com.chunlei.statistics.service;

import com.chunlei.statistics.domain.Account;
import com.chunlei.statistics.domain.timeseries.DataPoint;

import java.util.List;

public interface StatisticsService {

    List<DataPoint> findByAccountName(String name);

    DataPoint save(String accountName, Account account);
}
