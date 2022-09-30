package com.ros.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.Exception.NoOpenStockPeriodFound;
import com.ros.inventory.service.StockPeriod;

@RestController
@RequestMapping("/stockPeriod")
@CrossOrigin("*")
public class StockPeriodController {
	
	@Autowired
	StockPeriod service;
	
	@GetMapping("/getStartDate")
	public ResponseEntity<?> getStockPeriodStartDate(){
		ResponseEntity<?> response=null;
		try {
			response= new ResponseEntity<>(service.getStockPeriodStartDate(), HttpStatus.OK);
		} catch (NoOpenStockPeriodFound e) {
			// TODO Auto-generated catch block
			response= new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return response;
	}
}
