package com.ros.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.service.IProductManager;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/createNewPurchase")
@CrossOrigin("*")
public class ProductDtoController {
	
	@Autowired
	IProductManager prod;

	@GetMapping("/businessname/{bname}")
	@ResponseBody
	@Operation(summary = "Get the Items in the Product List based on Supplier Business Name")
	public ResponseEntity<?> get(@PathVariable(name="bname") String bname) throws InventoryException
	{
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getByName(bname),HttpStatus.OK);
		
		return response;
	}
	
	   @PutMapping("/updateQty")
	   @ResponseBody
	   @Operation(summary = "Updating  the Qty In the Create New PurchaseOrder")
	   public ResponseEntity<?> update(@RequestBody  List<ProductPDto> pr) throws InventoryException
	   {
		   ResponseEntity response;
		   response = new ResponseEntity<Object>(prod.updateProduct(pr),HttpStatus.OK);
			return response;	
	   }
	   
	   /*.................. Updating the Price................ */
	   @PutMapping("/updatePrice")
	   @ResponseBody
	   @Operation(summary = "Updating the Price In the Product List")
	   public ResponseEntity<?> updateProductPrice(@RequestBody  List<ProductPDto> price) throws InventoryException
	   {
		   ResponseEntity response;
		   response = new ResponseEntity<Object>(prod.updateProductPrice(price),HttpStatus.OK);
			return response;	
	   }

}
