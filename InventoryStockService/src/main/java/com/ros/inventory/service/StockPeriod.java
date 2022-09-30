package com.ros.inventory.service;

import java.time.LocalDate;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;

public interface StockPeriod {
	public LocalDate getStockPeriodStartDate() throws NoOpenStockPeriodFound;
    public double getClosingStockValue() throws NoOpenStockPeriodFound;
}
