package com.ros.inventory.service;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.CloseStockDetailDto;

public interface IStockEditService {

	String update_in(CloseStockDetailDto update_detail) throws InventoryException;

	
}