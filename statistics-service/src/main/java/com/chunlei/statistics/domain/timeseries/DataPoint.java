package com.chunlei.statistics.domain.timeseries;

import com.chunlei.statistics.domain.Currency;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
@Data
@Document(collection = "datapoints")
public class DataPoint {
    private DataPointId id;
    private Set<ItemMetric> incomes;
    private Set<ItemMetric> expenses;
    private Map<StatisticMetric, BigDecimal> statistics;
    private Map<Currency,BigDecimal> rates;
}
