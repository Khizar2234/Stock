package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.ApproveViewDto;
import com.ros.inventory.entities.PurchaseOrder;

@Mapper
@Component
public interface ApprovedViewMapper {
	@Mapping(source = "purchase.supplier", target = "supplier")
	@Mapping(source="purchase.purchasedId",target="purchasedNumber")
	@Mapping(source="purchase.purchaseOrderDate",target="purchaseDate")
	/*@Mapping(source = "purchase.product", target = "product")
	 * */
	
	ApproveViewDto convertToApproveViewDto(PurchaseOrder purchase);
}
