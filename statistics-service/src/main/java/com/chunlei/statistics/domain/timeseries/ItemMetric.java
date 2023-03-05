package com.chunlei.statistics.domain.timeseries;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemMetric {
    private String title;
    private BigDecimal amount;
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null|| getClass() != o.getClass()) return false;
        ItemMetric that = (ItemMetric) o;
        return title.equalsIgnoreCase(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
