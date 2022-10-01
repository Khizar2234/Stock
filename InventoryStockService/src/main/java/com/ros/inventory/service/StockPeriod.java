package com.ros.inventory.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.entities.CloseStock;

public interface StockPeriod {
	public Map<UUID, LocalDate> getStockPeriodStartDate() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getClosingStockValue() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getOpeningStockValue() throws NoOpenStockPeriodFound;
    public float totalClosedStockValue();
    public String saveClosingValue(List<ClosingValueDto> cvd) throws NoOpenStockPeriodFound;
    public List<CloseStock> getClosedStockList() throws NoOpenStockPeriodFound;

}
