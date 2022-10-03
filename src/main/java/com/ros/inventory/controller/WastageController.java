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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.wastageDto;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.service.IWastageManager;

@RestController
@RequestMapping("/wastage")
@CrossOrigin("*")
public class WastageController
{
  @Autowired
  IWastageManager wastagemanager;
  
  /*-----------------FOR ADDING WASTAGE PRODUCT ITEM------------------*/
  
  @PostMapping("/add")
  @ResponseBody
  public ResponseEntity<?> addWasteItem(@RequestBody  Wastage wastage)
  {
	  ResponseEntity response;
	  try
	  {
		  response = new ResponseEntity<Object>(wastagemanager.add(wastage),HttpStatus.OK);
	  }
	  catch(InventoryException e)
	  {
		  response= new ResponseEntity(e.getMessage(),HttpStatus.OK);
		  e.printStackTrace();
	  }
	  return response;
  }
  
  
  /*-------------- FOR GETTING ALL THE ITEM PRESENT IN WASTAGE -------------*/
   @GetMapping("/show")
   @ResponseBody
   public ResponseEntity<?> showAll()
   {
	   ResponseEntity response;
	   try
	   {
		   response = new ResponseEntity(wastagemanager.show(),HttpStatus.OK);
	   }
	   catch(InventoryException e)
	   {
		   response = new ResponseEntity(e.getMessage(),HttpStatus.OK);
		   e.printStackTrace();
	   }
	   return response;
   }
   
   /*------------- FOR SEARCHING ITEM BY NAME -------------------*/
   @GetMapping("/name/{item}")
   @ResponseBody
   public ResponseEntity<?> searchByName(@PathVariable(value="item") String item)
   {
	   ResponseEntity response;
	   try
	   {
		   response = new ResponseEntity(wastagemanager.byName(item),HttpStatus.OK);
	   }
	   catch(InventoryException e)
	   {
		   response = new ResponseEntity(e.getMessage(),HttpStatus.OK);
		   e.printStackTrace();
	   }
	   return response;
   }
   
   /*------------- FOR SEARCHING ITEM BY CODE -------------------*/
   @GetMapping("/code/{code}")
   @ResponseBody
   public ResponseEntity<?> searchByName(@PathVariable(value="code") long code)
   {
	   ResponseEntity response;
	   try
	   {
		   response = new ResponseEntity(wastagemanager.byId(code),HttpStatus.OK);
	   }
	   catch(InventoryException e)
	   {
		   response = new ResponseEntity(e.getMessage(),HttpStatus.OK);
		   e.printStackTrace();
	   }
	   return response;
   }
   
   /*------------ FOR DELETING THE PARTICULAR WASTAGE PRODUCT ITEM ---------------*/
   @DeleteMapping("/delete/{id}")
   @ResponseBody
   public ResponseEntity<?> delete(@PathVariable( value="id" ) UUID id)
   {
	   ResponseEntity response;
	   try
	   {
		   response = new ResponseEntity(wastagemanager.delete(id),HttpStatus.OK);
	   }
	   catch(InventoryException e)
	   {
		   response = new ResponseEntity(e.getMessage(),HttpStatus.OK);
		   e.printStackTrace();
	   }
	   return response;
   }
   

}
