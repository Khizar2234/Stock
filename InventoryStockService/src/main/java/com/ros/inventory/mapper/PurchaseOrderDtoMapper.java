package com.ros.inventory.mapper;

import java.time.LocalDate;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.purchaseOrderDto;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.PurchaseOrder;

@Mapper
@Component
public interface PurchaseOrderDtoMapper {
	
	@Mapping(source="purchase.purchasedId",target="purchasedId")
	@Mapping(source="purchase.purchaseOrderDate",target="purchaseOrderDate")
	@Mapping(source="purchase.totalAmount",target="totalAmount")
	@Mapping(source="purchase.purchaseOrderStatus",target="purchaseOrderStatus")
	@Mapping(source="purchase.supplier.supplierBasic.supplierBusinessName",target="supplierBusinessName")
	
	purchaseOrderDto convertTopurchaseOrderDto(PurchaseOrder purchase);
	PurchaseOrder convertToPurchaseOrder(purchaseOrderDto purchase);
	

}