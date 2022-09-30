package com.ros.inventory.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.StockPeriod;

@Repository
public interface StockPeriodRepo extends JpaRepository<StockPeriod, UUID>{

}
