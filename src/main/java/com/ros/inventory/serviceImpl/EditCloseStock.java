package com.ros.inventory.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.OpeningStockRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.controller.dto.CloseStockDetailDto;
import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.controller.dto.purchaseOrderDto;
import com.ros.inventory.controller.dto.wastageDto;
import com.ros.inventory.mapper.OpenStockMapper;
import com.ros.inventory.mapper.PurchaseOrderDtoMapper;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.service.IStockEditService;
@Service
public class EditCloseStock implements IStockEditService {
	
	
	@Autowired
	OpeningStockRepository open_repo;
	@Autowired
	PurchaseRepository purchase_repo;
	@Autowired
	OpeningStockDto open_dto;
	@Autowired
	purchaseOrderDto purchase_dto;
	@Autowired
	OpenStockMapper open_mapper;
	@Autowired
	PurchaseOrderDtoMapper purchase_mapper;
	@Autowired
	wastageDto ws_dto;

	@Override
	public String update_in(CloseStockDetailDto update_detail) throws InventoryException {
		// TODO Auto-generated method stub
		
		
		
		for(OpeningStockDto s:update_detail.getOpen_stock()) {
			OpeningStockDto open=new OpeningStockDto();
			open.setProductCode(s.getProductCode());
			open.setPricePerUnit(s.getPricePerUnit());
			open.setProductName(s.getProductName());
			open.setQty(s.getQty());
			open.setUnitMeasurement(s.getUnitMeasurement());
			open_repo.save(open_mapper.convertToOpeningStock(open));
			
		}
		
		for(purchaseOrderDto p:update_detail.getPurchase_order()) {
			purchaseOrderDto purchase=new purchaseOrderDto();
			
			purchase.setPurchasedId(p.getPurchasedId());
			purchase.setPurchaseOrderDate(p.getPurchaseOrderDate());
			purchase.setPurchaseOrderStatus(p.getPurchaseOrderStatus());
			purchase.setSupplierBusinessName(p.getSupplierBusinessName());
			purchase.setTotalAmount(p.getTotalAmount());
			
			purchase_repo.save(purchase_mapper.convertToPurchaseOrder(purchase));
		}
		
		for(wastageDto wss:update_detail.getWastage()) {
			wastageDto ws=new wastageDto();
			ws.setProductCode(wss.getProductCode());
			ws.setProductName(wss.getProductName());
			ws.setPricePerUnit(wss.getPricePerUnit());
			ws.setUnitMeasurement(wss.getUnitMeasurement());
			ws.setWastageId(wss.getWastageId());
		}
		return "Updated";
	}

}
