package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import org.springframework.util.MultiValueMap;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.wastageDto;
import com.ros.inventory.entities.Wastage;

public interface IWastageManager {

	Wastage add(wastageDto wastage) throws InventoryException;

	//List<Wastage> show() throws InventoryException;

	Wastage byName(String item) throws InventoryException;

	Wastage byId(long code) throws InventoryException;

	Wastage delete(UUID id) throws InventoryException;

}
