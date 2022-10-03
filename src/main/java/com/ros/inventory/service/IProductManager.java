package com.ros.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.Product;

public interface IProductManager
{

	List<Product> getProduct(UUID id) throws InventoryException;
	Product delete(UUID id) throws InventoryException;
//	Product addProduct(long id, Product add)throws InventoryException;
	Product update(Product product)throws InventoryException;

/*---------------------------------------------------------------------------------*/

	List<ProductDto> getByName(String bname) throws InventoryException;

	//void updateQty(long id, String qty);

	String updateProduct(List<ProductPDto> pr)throws InventoryException;
	
	//update the price
	String updateProductPrice(List<ProductPDto> price)throws InventoryException;
	
	
	//List<Product> getProduct(UUID id) throws InventoryException;
	//Product delete(UUID id) throws InventoryException;
//	Product addProduct(long id, Product add)throws InventoryException;
	//Product update(Product product)throws InventoryException;
	

}
