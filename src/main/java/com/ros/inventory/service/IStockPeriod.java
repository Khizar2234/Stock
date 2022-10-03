package com.ros.inventory.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.Exception.OpeningStockNotFound;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.controller.dto.EditInfo;
import com.ros.inventory.controller.dto.SiteTransferDto;
import com.ros.inventory.entities.SiteTransfer;
import com.ros.inventory.entities.StockPeriod;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.entities.CloseStock;

public interface IStockPeriod {
	public Map<UUID, LocalDate> getStockPeriodStartDate() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getClosingStockValue() throws NoOpenStockPeriodFound;
    public Map<UUID, Double> getOpeningStockValue() throws NoOpenStockPeriodFound;
    public float totalClosedStockValue();
    public String saveClosingValue(List<ClosingValueDto> cvd) throws NoOpenStockPeriodFound;
    public List<CloseStock> getClosedStockList() throws NoOpenStockPeriodFound;
    public boolean closeStockApprove(CloseStock closedStock);
    public List<Wastage> getAllWastedProducts(UUID id);
    public List<SiteTransferDto> getAllSiteTransfers(UUID id); 
    public StockPeriod add(StockPeriod sp);
	public EditInfo getEditInfo(UUID id);

}
