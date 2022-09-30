package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.StockPeriodDto;
import com.ros.inventory.entities.StockPeriod;

@Mapper
@Component

public interface StockPeriodMapper {
	@Mapping(source="sp.startDate",target = "startDate")
	@Mapping(source="sp.closeDate",target = "closeDate")
	StockPeriodDto convertToDto(StockPeriod sp);
}
