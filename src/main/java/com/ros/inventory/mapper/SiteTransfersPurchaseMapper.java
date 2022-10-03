package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.SiTeTransfersDto;
import com.ros.inventory.entities.PurchaseOrder;

@Mapper
@Component
public interface SiteTransfersPurchaseMapper {
	@Mapping(source = "supplier.supplierBasic.supplierBusinessName", target = "supplierName")
	@Mapping(source="purchase.purchaseOrderDate",target="date")
	@Mapping(source="purchase.transferType",target="transferType")
	SiTeTransfersDto convertToSiTeTransfersDto(PurchaseOrder purchase);
}
