package com.ros.inventory.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ros.inventory.entities.StockPeriod;

public interface StockPeriodRepo extends JpaRepository<StockPeriod, UUID>{

}
