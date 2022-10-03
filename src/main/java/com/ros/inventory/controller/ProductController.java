package com.ros.inventory.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.entities.Product;
import com.ros.inventory.service.IProductManager;

import io.swagger.v3.oas.annotations.Operation;

@RestController

@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController
{

	@Autowired
	IProductManager productmanager;
	
	/* ----------------- FOR DISPLAYING THE LIST OF PRODUCT THAT PARTICULAR SUPPLIER HAS ---------------------------*/
	/*--------------- HERE WE HAVE TO PASS SUPPLIER ID------------------*/
	@GetMapping("/show/{id}")
	@ResponseBody
	@Operation(summary="gets list of product for a supplier")
	public ResponseEntity<?> get(@PathVariable(value="id") UUID id)
	{
		ResponseEntity<?> response;
		try
		{
			response = new ResponseEntity<>(productmanager.getProduct(id),HttpStatus.OK);
		}
		catch(InventoryException e)
		{

			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
			e.printStackTrace();
		}
		
		return response;
	}
	
//    @PostMapping("/addproduct/{id}")
//    @ResponseBody
//    public ResponseEntity<?> addProduct(@PathVariable(value = "id") long id,@RequestBody Product  add)
//    {
//   	 ResponseEntity<?> response;
//   	 try
//   	 {
//   		 response = new ResponseEntity<>(productmanager.addProduct(id,add),HttpStatus.OK);
//   	 }
//   	 catch(InventoryException e)
//   	 {
//   		 response = new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
//   		 e.printStackTrace();
//   	 }
//   	 return response;
//    }
//    
	  /*--------------------FOR DELETING THE PARTICULAR ITEM -----------------------------*/
	   @DeleteMapping("/delete/{id}")
	   @ResponseBody
	   public ResponseEntity<?> delete(@PathVariable(value="id") UUID id)
	   {
		   ResponseEntity<?> response;
		   try
		   {
			   response = new ResponseEntity<>(productmanager.delete(id),HttpStatus.OK);
		   }
		   catch (InventoryException e)
			{
				response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
	   }
	  
	   /*-----------------FOR UPDATING THE PARTICULAR PRODUCT ITEM  ------------------------------- */
	   @PutMapping("/update")
	   @ResponseBody
	   public ResponseEntity<?> update(@RequestBody Product product)
	   {
		   ResponseEntity<?> response;
		   try
		   {
			   response = new ResponseEntity<>(productmanager.update(product),HttpStatus.OK);
		   }
		   catch (InventoryException e)
			{
				response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
				e.printStackTrace();
			}
			return response;	
	   }
}
