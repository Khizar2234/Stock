package com.ros.inventory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentsDto {
	private String discrepency;
	private String creditNote;
	private String invoiceReceived;
}
