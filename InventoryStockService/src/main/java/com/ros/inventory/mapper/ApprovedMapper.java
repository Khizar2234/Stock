package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.ApprovedDto;
import com.ros.inventory.controller.dto.DraftsDto;
import com.ros.inventory.entities.PurchaseOrder;
@Mapper
@Component
public interface ApprovedMapper {
	//@Mapping(source = "purchase.supplier", target = "supplier")
	@Mapping(source = "supplier.supplierBasic.supplierBusinessName", target = "supplierName")
	@Mapping(source = "supplier.supplierType", target = "supplierType")

	@Mapping(source="purchase.purchasedId",target="purchasedNumber")
	@Mapping(source="purchase.purchaseOrderDate",target="purchaseDate")
	@Mapping(source="purchase.purchaseOrderStatus",target="status")
	ApprovedDto convertToApprovedDto(PurchaseOrder purchase);

}
