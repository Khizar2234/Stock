package com.ros.inventory.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;

public interface StockPeriod {
	public LocalDate getStockPeriodStartDate() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getClosingStockValue() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getOpeningStockValue() throws NoOpenStockPeriodFound;
}
