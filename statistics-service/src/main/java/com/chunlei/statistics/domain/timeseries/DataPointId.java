package com.chunlei.statistics.domain.timeseries;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class DataPointId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String account;
    private Date date;
}
