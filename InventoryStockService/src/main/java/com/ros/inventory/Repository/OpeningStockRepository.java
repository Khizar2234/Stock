package com.ros.inventory.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.ClosingStock;
import com.ros.inventory.entities.Invoice;
import com.ros.inventory.entities.OpeningStock;

@Repository
public interface OpeningStockRepository extends JpaRepository<OpeningStock, Long>{
    @Query(value = "select o from OpeningStock o where o.stockPeriod.StockPeriodId =:stockPeriodId")
    public List<OpeningStock> findByStockPeriodId(@Param(value = "stockPeriodId") UUID stockPeriodId);
}
