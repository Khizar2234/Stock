package com.ros.inventory.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Invoice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="invoice_id", length = 8)
	private UUID invoiceId;
	
	//@column(name="to")
	private double netBalance;
	
	private double vat;
	
	
	 @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
	 @JoinColumn(name ="purchase_id")
	 private PurchaseOrder purchase;
	
	 @OneToOne(fetch = FetchType.LAZY ,cascade=CascadeType.ALL)
	 @JoinColumn(name ="restaurant_id")
	 private Restaurant restaurant;
//	private Supplier supplierId;
	
	
}
