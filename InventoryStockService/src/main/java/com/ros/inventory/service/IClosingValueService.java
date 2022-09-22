package com.ros.inventory.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.ClosingValueDto;

public interface IClosingValueService {

	List<ClosingValueDto> getValues() throws InventoryException;

	String  setValues(List<ClosingValueDto> close_stock_values) throws InventoryException;

}
