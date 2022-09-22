package com.ros.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.ros.inventory.controller.dto.AttachmentsDto;
import com.ros.inventory.entities.Attachments;

@Mapper
@Component
public interface AttachmentsMapper {
	@Mappings({ @Mapping(source = "attachments.discrepency ", target = "discrepency"),
			@Mapping(source = "attachments.creditNote ", target = "creditNote"),
			@Mapping(source = "attachments.invoiceReceived ", target = "invoiceReceived") })
	Attachments convertToAttachments(AttachmentsDto attachments);

	AttachmentsDto convertToAttachmentsDto(Attachments attachments);
}
