package com.ros.inventory.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Supplier implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "supplier_id", length = 8)
	private UUID supplierId;

	@Enumerated(EnumType.STRING)
	private SupplierType supplierType;

	@Column(name = "restaurant_name")
	private String restaurantName;

	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id")
	private List<Product> products;

	
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierbank_id")
	private SupplierBankInfo supplierBank;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierbasic_id")
	private SupplierBasicInfo supplierBasic;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplieraddress_id")
	private Address supplierAddress;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "supplierconytact_id")
	private SupplierContact supplierContact;

}
