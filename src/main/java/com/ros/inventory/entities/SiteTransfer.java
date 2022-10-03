package com.ros.inventory.entities;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SiteTransfer {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "site_transfer_id", length = 8)
	private UUID siteTransferId;

	@Column(name = "date")
	private Date date;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SiteTransferProduct> siteTransferProducts;
	
	@Column(name = "transferType")
	private TransferType transferType;

	@ManyToOne
	private Supplier supplier;

}
