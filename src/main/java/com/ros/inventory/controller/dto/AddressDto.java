package com.ros.inventory.controller.dto;

import java.util.UUID;

import com.ros.inventory.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto
{
   private UUID supplierId;
   private Address  address;
}
