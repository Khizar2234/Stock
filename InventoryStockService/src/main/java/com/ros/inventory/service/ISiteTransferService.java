package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.entities.SiteTransferProduct;

public interface ISiteTransferService {
	public List<SiteTransferProduct> getAllProducts(UUID id) throws Exception;

	void addAllProducts(UUID id, List<SiteTransferProduct> prods) throws Exception;

}
