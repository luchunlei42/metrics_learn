package com.chunlei.statistics.repository;

import com.chunlei.statistics.domain.timeseries.DataPoint;
import com.chunlei.statistics.domain.timeseries.DataPointId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, DataPointId> {
    List<DataPoint> findByIdAccount(String account);
}
