package com.ros.inventory.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.ClosingStock;

@Repository
public interface ClosingStockRepository extends JpaRepository<ClosingStock, UUID>{

    @Query(value = "select c from ClosingStock c where c.stockPeriod.StockPeriodId =:stockPeriodId")
    public List<ClosingStock> findByStockPeriodId(@Param(value = "stockPeriodId") UUID stockPeriodId);
}
