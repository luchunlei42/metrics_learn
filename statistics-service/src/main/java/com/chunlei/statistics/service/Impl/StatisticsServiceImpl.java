package com.chunlei.statistics.service.Impl;

import com.chunlei.statistics.domain.Account;
import com.chunlei.statistics.domain.Currency;
import com.chunlei.statistics.domain.Item;
import com.chunlei.statistics.domain.Saving;
import com.chunlei.statistics.domain.timeseries.DataPoint;
import com.chunlei.statistics.domain.timeseries.DataPointId;
import com.chunlei.statistics.domain.timeseries.ItemMetric;
import com.chunlei.statistics.domain.timeseries.StatisticMetric;
import com.chunlei.statistics.repository.DataPointRepository;
import com.chunlei.statistics.service.ExchangeRatesService;
import com.chunlei.statistics.service.StatisticsService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private ExchangeRatesService exchangeRatesService;
    @Autowired
    private DataPointRepository repository;
    @Override
    public List<DataPoint> findByAccountName(String name) {
        return repository.findByIdAccount(name);
    }

    @Override
    public DataPoint save(String accountName, Account account) {
        Instant instant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        DataPointId pointId = new DataPointId(accountName,Date.from(instant));
        Set<ItemMetric> incomes = account.getIncomes().stream()
                .map(this::createItemMetric)
                .collect(Collectors.toSet());
        Set<ItemMetric> expenses = account.getExpenses().stream()
                .map(this::createItemMetric)
                .collect(Collectors.toSet());
        Map<StatisticMetric,BigDecimal> statistics = createStatisticsMetrics(incomes,expenses, account.getSaving());

        DataPoint dataPoint = new DataPoint();
        dataPoint.setId(pointId);
        dataPoint.setIncomes(incomes);
        dataPoint.setExpenses(expenses);
        dataPoint.setStatistics(statistics);
        dataPoint.setRates(exchangeRatesService.getCurrentRates());

        return repository.save(dataPoint);
    }
    private Map<StatisticMetric,BigDecimal> createStatisticsMetrics(Set<ItemMetric> incomes,
                            Set<ItemMetric> expenses, Saving saving){
        BigDecimal savingAmount = exchangeRatesService.convert(saving.getCurrency(),Currency.getBase(),saving.getAmount());
        BigDecimal expensesAmount = expenses.stream()
                .map(ItemMetric::getAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal incomesAmount = incomes.stream()
                .map(ItemMetric::getAmount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return ImmutableMap.of(StatisticMetric.EXPENSES_AMOUNT,expensesAmount,
                StatisticMetric.INCOMES_AMOUNT,incomesAmount,
                StatisticMetric.SAVING_AMOUNT,savingAmount);
    }
    private ItemMetric createItemMetric(Item item){
        BigDecimal amount = exchangeRatesService
                .convert(item.getCurrency(), Currency.getBase(),item.getAmount())
                .divide(item.getPeriod().getBaseRatio(),4, RoundingMode.HALF_DOWN);
        return new ItemMetric(item.getTitle(),amount);
    }
}
