package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.AddProductDto;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.Supplier;

@Mapper
@Component

public interface AddProductMapper {
	@Mappings({ @Mapping(source = "s.supplierId", target = "supplierId"),
			@Mapping(source = "s.products", target = "products") })
	AddProductDto convertToDto(Supplier s);

	Supplier convertToEntity(AddProductDto add);


}
