package com.ros.inventory.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankDto
{
	private String bankName;
	private String bankBranchName;
	private String accountHolderName;
	private long accountNumber;
	private String bankIFSCCode;
}
