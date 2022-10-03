package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.entities.PurchaseOrder;
@Mapper
@Component
public interface PurchaseOrderMapper {

	@Mapping(source = "supplier.supplierBasic.supplierBusinessName", target = "supplierName")
	@Mapping(source = "supplier.supplierType", target = "supplierType")
	@Mapping(source="purchase.purchasedId",target="purchasedNumber")
	@Mapping(source="purchase.purchaseOrderDate",target="purchaseDate")
	DraftsDto convertToPurchaseDto(PurchaseOrder purchase);
	PurchaseOrder convertToPurchaseOrder(DraftsDto draftDto);


}
