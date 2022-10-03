package com.ros.inventory.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.entities.CloseStock;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.controller.dto.CloseStockDetailDto;
import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.controller.dto.Summary;
import com.ros.inventory.controller.dto.purchaseOrderDto;
import com.ros.inventory.controller.dto.stock_balance;
import com.ros.inventory.controller.dto.wastageDto;

public interface stockService {

	List<OpeningStockDto> getOpenStock() throws InventoryException;

//	String addWastage(wastageDto ws) throws InventoryException;

	List<wastageDto> showWastage() throws InventoryException;

//	String deleteWastage(Long id) throws InventoryException;

	List<purchaseOrderDto> getpurchaseOrder() throws InventoryException;

	List<stock_balance> getStockBalance() throws InventoryException;

	Summary getSummary() throws InventoryException;

	Double getTotal() throws InventoryException;

	Double getPurchaseTotal() throws InventoryException;

	Double getwastageTotal() throws InventoryException;

	Double getSales() throws InventoryException;

	Double get_stock_balance_total() throws InventoryException;

	LocalDate get_stock_start_date() throws InventoryException;

	List<CloseStock> get_view_close_stock() throws InventoryException;

	String add_close_stock(String status) throws InventoryException;

	CloseStockDetailDto view_stock_detail_date(UUID stockID) throws InventoryException;

	String approved_stock_period(UUID stockID) throws InventoryException;

	String bulk_approved_stock_period(List<CloseStockDto> close_stock_bulk) throws InventoryException;

	String back_real_time();

	// List<CloseStockDetailDto> edit_closed_stock(List<CloseStockDetailDto>
	// closed_stock) throws InventoryException;

}
