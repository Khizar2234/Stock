package com.ros.inventory.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.ros.inventory.entities.CloseStock;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.service.IProductManager;
import com.ros.inventory.service.stockService;
import com.ros.inventory.controller.dto.CloseStockDetailDto;
import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.wastageDto;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {

	@Autowired
	stockService prod;

	// @Autowired
	// StockApprovalService stockApproval;
	// 1 ------------------------Open Stock list-------------------------------//
	@GetMapping("/openStock")
	@ResponseBody
	public ResponseEntity<?> get() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getOpenStock(), HttpStatus.OK);

		return response;
	}

//	//2 --------------------Add In Wastage product-----------------------------//
//	@PostMapping("/addWastage")
//	@ResponseBody
//	public ResponseEntity<?> addWastage(@RequestBody wastageDto ws) throws InventoryException {
//		ResponseEntity<?> response;
//		response = new ResponseEntity<>(prod.addWastage(ws), HttpStatus.OK);
//
//		return response;
//	}
	// 3 ----------------------Show Wastage List--------------------------------//

	@GetMapping("/showWastage")
	@ResponseBody
	public ResponseEntity<?> showWastage() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.showWastage(), HttpStatus.OK);

		return response;
	}
//	//4 ----------------------Delete Wastage By ID------------------------------//
//
//	@DeleteMapping("/deleteWastage/{id}")
//	@ResponseBody
//	public ResponseEntity<?> deleteWastage(@PathVariable("id") Long id) throws InventoryException {
//		ResponseEntity<?> response;
//		response = new ResponseEntity<>(prod.deleteWastage(id), HttpStatus.OK);
//
//		return response;
//	}

	// 5--------------------PurchaseOrderDto------------------------------//

	@GetMapping("/purchaseOrderDto")
	@ResponseBody
	public ResponseEntity<?> getpurchaseOrder() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getpurchaseOrder(), HttpStatus.OK);

		return response;
	}

	// 6 --------------------stock balance----------------------------------//

	@GetMapping("/stockBalance")
	@ResponseBody
	public ResponseEntity<?> getstockBalance() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getStockBalance(), HttpStatus.OK);

		return response;
	}

	// 7 -----------------------Summary--------------------------------------//

	@GetMapping("/summary")
	@ResponseBody
	public ResponseEntity<?> getSummary() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getSummary(), HttpStatus.OK);

		return response;
	}
	// 8 -----------------------Open Stock Total value------------------------//

	@GetMapping("/openTotalValue")
	@ResponseBody
	public ResponseEntity<?> getOpenTotal() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getTotal(), HttpStatus.OK);
		return response;
	}

	// 9----------------------Purchase Total value--------------------------//
	@GetMapping("/purchaseTotalValue")
	@ResponseBody
	public ResponseEntity<?> getPurchaeTotal() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getPurchaseTotal(), HttpStatus.OK);
		return response;
	}

	// 10 ---------------------wastage Total Value ---------------------------//
	@GetMapping("/wastageTotalValue")
	@ResponseBody
	public ResponseEntity<?> getwastageTotal() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.getwastageTotal(), HttpStatus.OK);
		return response;
	}

	// 11------------------- Net Sales Value -----------------------------//
//	@GetMapping("/netSales")
//	@ResponseBody
//	public ResponseEntity<?> getSales() throws InventoryException {
//		ResponseEntity<?> response;
//		response = new ResponseEntity<>(prod.getSales(), HttpStatus.OK);
//		return response;
//	}

	// 12------------------stock balance total values --------------------//
	@GetMapping("/stockBalanceTotalValue")
	@ResponseBody
	public ResponseEntity<?> get_stock_balace_total() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.get_stock_balance_total(), HttpStatus.OK);
		return response;
	}

	// 13-------------------start date of the session ----------------------//
	@GetMapping("/stock_start_date")
	@ResponseBody
	public ResponseEntity<?> get_stock_start_date() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.get_stock_start_date(), HttpStatus.OK);
		return response;
	}

	// ---------------------view close stock ----------------------------//
	@GetMapping("/view_close_stock")
	@ResponseBody
	public ResponseEntity<?> get_view_close_stock() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.get_view_close_stock(), HttpStatus.OK);
		return response;
	}

	// --------------------Add close stock or Save the close stock as draft for a
	// stock period------------------------------//
	// -------------------------use this single api to do two tasks
	// -------------------------------------------------------------//
//	@PostMapping("/add_close_stock/{status}")
//	@ResponseBody
//	public ResponseEntity<?> add_close_stock(@PathVariable("status") String status) throws InventoryException{
//		ResponseEntity<?> response;
//		response=new ResponseEntity<>(prod.add_close_stock(status),HttpStatus.OK);
//		return response;
//	}
	// --------------------View a closed stock period in detail---------------//
	@GetMapping("/view_close_stock_by_stockID/{stockID}")
	@ResponseBody
	public ResponseEntity<?> view_stock_detail_date(@PathVariable("stockID") UUID stockID) throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.view_stock_detail_date(stockID), HttpStatus.OK);
		return response;
	}

	// ------------------------Approve a stock period for a closed
	// stock------------------------------------//

	@PutMapping("/approved_stock_period/{stockID}")
	@ResponseBody
	public ResponseEntity<?> approved_stock_period(@PathVariable("stockID") UUID stockID) throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.approved_stock_period(stockID), HttpStatus.OK);
		return response;
	}
	// ------------------- Bulk approval of the stock period from the closed stock
	// list ------------//

	@PutMapping("/bulk_approval")
	@ResponseBody
	public ResponseEntity<?> bulk_approved_stock_period(@RequestBody List<CloseStockDto> close_stock_bulk)
			throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.bulk_approved_stock_period(close_stock_bulk), HttpStatus.OK);
		return response;
	}

	@PutMapping("/backInCurrentTime")
	@ResponseBody
	public ResponseEntity<?> back_real_time() throws InventoryException {
		ResponseEntity<?> response;
		response = new ResponseEntity<>(prod.back_real_time(), HttpStatus.OK);
		return response;
	}
	
}
