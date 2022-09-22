package com.ros.inventory.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SupplierBankInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="bank_id", length = 8)
	private UUID bankInfoId;
	
	private String bankName;
	
	private String bankBranchName;
	
	private String accountHolderName;
	
	private long accountNumber;
	
	private String bankIFSCCode;
	
	

}
