package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.SiteTransferDto;
import com.ros.inventory.entities.SiteTransfer;

@Mapper
@Component
public class SiteTransferMapper {
	
	public SiteTransferDto toDto(SiteTransfer st) {
		SiteTransferDto dto = new SiteTransferDto();
		dto.setSiteTransferDtoId(st.getSiteTransferId());
		dto.setDate(st.getDate());
		dto.setNoOfProducts(st.getSiteTransferProducts().size());
		dto.setTransferType(st.getTransferType());
		double amount = st.getSiteTransferProducts().stream().mapToDouble(product -> product.getPricePerUnit()*product.getQty()).sum();
		dto.setTotalValue(amount);
		dto.setSupplierName(st.getSupplier().getRestaurantName());
		return dto;
        
    }


}
