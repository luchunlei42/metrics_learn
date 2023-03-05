package com.chunlei.statistics.controller;

import com.chunlei.statistics.domain.Account;
import com.chunlei.statistics.domain.timeseries.DataPoint;
import com.chunlei.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/current")
    public List<DataPoint> getCurrentAccountStatistics(Principal principal){
        return statisticsService.findByAccountName(principal.getName());
    }

    @PreAuthorize("hasAuthority('SCOPE_server')")
    @GetMapping("/{accountName}")
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName){
        return statisticsService.findByAccountName(accountName);
    }
    //@PreAuthorize("hasAuthority('SCOPE_all')")
    @PutMapping("/{accountName}")
    public void saveAccountStatistics(@PathVariable String accountName, @RequestBody Account account){
        statisticsService.save(accountName,account);
    }


}
