package com.ros.inventory.service;

import java.util.Date;
import java.util.List;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.ClosingValueDto;
import com.ros.inventory.entities.CloseStock;

public interface IClosingValueService {

	List<ClosingValueDto> getValues() throws InventoryException;

	String  setValues(List<ClosingValueDto> close_stock_values) throws InventoryException;

    List<CloseStock> getStockPeriod(Date start_Date, Date end_date) throws InventoryException;

}
