package com.ros.inventory.service;

import java.time.LocalDate;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;

public interface StockPeriod {
	public LocalDate getStockPeriodStartDate() throws NoOpenStockPeriodFound;
}
