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
@Table(name="address")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Address implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="address_id", length = 8)
	private UUID addressId;

	private String street;
	
	private String area;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private long pincode;
	


}
