package com.ros.inventory.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;
import com.ros.inventory.controller.dto.ClosingValueDto;

public interface StockPeriod {
	public LocalDate getStockPeriodStartDate() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getClosingStockValue() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getOpeningStockValue() throws NoOpenStockPeriodFound;
    public float totalClosedStockValue();
    public String saveClosingValue(List<ClosingValueDto> cvd) throws NoOpenStockPeriodFound;

}
