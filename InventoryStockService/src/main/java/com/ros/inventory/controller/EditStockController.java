package com.ros.inventory.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.controller.dto.CloseStockDetailDto;
import com.ros.inventory.service.IStockEditService;
import com.ros.inventory.service.stockService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/edit")
@CrossOrigin("*")
public class EditStockController {

	@Autowired
	IStockEditService prod;
	
	@PutMapping("/updatestock")
	@ResponseBody
	public ResponseEntity<?> update_in(@RequestBody CloseStockDetailDto update_detail ) throws InventoryException{
		ResponseEntity<?> response;
		response=new ResponseEntity(prod.update_in(update_detail),HttpStatus.OK);

			return response;
	}
}