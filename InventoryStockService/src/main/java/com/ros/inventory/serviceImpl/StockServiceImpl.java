package com.ros.inventory.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.CloseStockRepository;
import com.ros.inventory.Repository.InvoiceRepository;
import com.ros.inventory.Repository.OpeningStockRepository;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.PurchaseRepository;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.Repository.WastageRepository;
import com.ros.inventory.entities.CloseStock;
import com.ros.inventory.entities.Invoice;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.OrderStatus;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.PurchaseOrder;
import com.ros.inventory.entities.StockStatus;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.entities.Wastage;
import com.ros.inventory.mapper.CloseStockDtoMapper;
import com.ros.inventory.mapper.OpenStockMapper;
//import com.ros.inventory.mapper.purchaseOrderDtoMapper;
import com.ros.inventory.mapper.wastageDtoMapper;
import com.ros.inventory.service.stockService;
import com.ros.inventory.controller.dto.CloseStockDetailDto;
import com.ros.inventory.controller.dto.CloseStockDto;
import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.controller.dto.StockBalanceDto;
import com.ros.inventory.controller.dto.Summary;
import com.ros.inventory.controller.dto.purchaseOrderDto;
import com.ros.inventory.controller.dto.wastageDto;

@Service
public class StockServiceImpl implements stockService {

	@Autowired
	ProductRepository repo;
	@Autowired
	OpenStockMapper mapper;
	@Autowired
	WastageRepository wsrepo;
	@Autowired
	wastageDtoMapper wastmapper;
	@Autowired
	PurchaseRepository purchaseRepo;

	@Autowired
	InvoiceRepository invoicerepo;
	@Autowired
	SupplierRepository supplierRepo;
	@Autowired
	CloseStockRepository closerepo;
//	@Autowired
//	PurchaseOrderSiteTransferManager site_transfer;
	@Autowired
	OpeningStock open_stock;
	@Autowired
	OpeningStockRepository opening_repo;
	@Autowired
	ClosingValuesServiceImpl closeValuesImpl;
	@Autowired
	CloseStockDtoMapper close_mapper;

	private double open_total = 0, purchase_total = 0, wastage_total = 0, net_sales = 0, stock_balance_total = 0;
	LocalDate  stock_close_date, stock_start_date, stock_period_start;
	double closing_stock_values, opening_stock_values, cost_of_sales;
	
	
	

	// ---------------------Opening Stock------------------------//
	@Override
	public List<OpeningStockDto> getOpenStock() {
		
		// declare the open total to get the total value of opening stock
		double open_total = 0;
		
		// purchase order object reference
		PurchaseOrder first_purchase;
		
        // It return the first purchase data whose purchase order status should be exported.
		if(closerepo.findAll().isEmpty()) {
			
			// It return the first purchase order object 
		    first_purchase = purchaseRepo.getLastSession("exported");
		}
		else {
			// It return the first purchase object with exported status but it should be after the last closed end date
			first_purchase = purchaseRepo.getStartSessionDate("exported", closerepo.OpeningDate().getStock_end_date());
		}
		
		// OpeningStockDto list 
		List<OpeningStockDto> list = new ArrayList();

		// first_purchase should not be null
		if(first_purchase != null) {
		
	    // set the session start date 
		LocalDate session_start = first_purchase.getPurchaseOrderDate();
		
		
		// get data from database
		List<OpeningStock> dataFromDB = opening_repo.findAll();

		// It return the last closing value
		CloseStock dataFrom = closerepo.OpeningDate();

		// declaration of opening and closing date
		LocalDate opening_date, opening_close_date;

		// It check if there is not any element in close stock, it not allow to enter
		if (closerepo.findAll().isEmpty() == false) {

			opening_date = dataFrom.getStock_start_date();
			opening_close_date = dataFrom.getStock_end_date();

		} 
		else {
			opening_date = session_start;
			opening_close_date = LocalDate.now();
		}
		
		// Here we set the start and closing value if the value is not available in close stock it return the specified values.
		LocalDate closing_dat = stock_close_date != null ? stock_close_date : opening_close_date;
		LocalDate start_date = stock_start_date != null ? stock_start_date : opening_date;

		// use predicate so to check this month data is available or not
		Predicate<OpeningStock> s1 = s -> s.getClosing_date().isAfter(start_date)
				|| s.getClosing_date().isEqual(start_date);
		boolean isPresent = dataFromDB.stream().anyMatch(s1);
		
		// if this month data is available then it allow the program to enter the if
		// condition
		if (isPresent) {

			// It is use to filter the data between given date
			List<OpeningStock> list1 = dataFromDB.stream()
					.filter(x -> (x.getClosing_date().isAfter(start_date) && x.getClosing_date().isBefore(closing_dat)
							|| x.getClosing_date().isEqual(start_date) || x.getClosing_date().equals(closing_dat)))
					.collect(Collectors.toList());

			// Inside for loop , store the objects in a list and also add open_total
			for (OpeningStock p : list1) {
				// add total
				open_total += p.getPricePerUnit() * p.getQty();
				// store product inside arrayList
				list.add(mapper.convertToOpeningStockDto(p));
			}

			// assign open_total
			this.open_total = open_total;

		} else {
			return list;
		}
		return list;
		}
		return list;
	}

	// -------------------------------Show Wastage-----------------------//
	@Override
	public List<wastageDto> showWastage() throws InventoryException {

		double wastage_total = 0;
		
		// get all the wastage data from database
		List<Wastage> dataFromDB = wsrepo.findAll();

		// declaration the list of wastageDto
		List<wastageDto> list = new ArrayList();
		
		
		// check the database should not be empty
		if(dataFromDB.size() !=0) {
		
	// It return the first purchase order exported values
			PurchaseOrder first_purchase;
	        // It return the last exported data.
			if(closerepo.findAll().isEmpty()) {
				
				// It is return first purchse order , it should be exported status
			    first_purchase = purchaseRepo.getLastSession("exported");
			}
			else {
				// It return the first purchase object with exported status but it should be after the last closed end date
				first_purchase = purchaseRepo.getStartSessionDate("exported", closerepo.OpeningDate().getStock_end_date());
			}

		// Last_purchase should be null
		if(first_purchase != null) {
		
		// set the start session 
		LocalDate session_start = first_purchase.getPurchaseOrderDate();
		
		
		// set start and close date
		LocalDate closing_dat = stock_close_date != null ? stock_close_date : LocalDate.now();
		LocalDate start_date = stock_start_date != null ? stock_start_date : session_start;
		
	
		
		// use predicate so to check this month data is available or not
		Predicate<Wastage> s1 = s -> s.getWastageDate().isAfter(start_date) || s.getWastageDate().isEqual(start_date);
		boolean isPresent = dataFromDB.stream().anyMatch(s1);

		// if this month data is available then it allow the program to enter the if
		// condition
		if (isPresent) {
			
			
			// It is used to filter the wastage data between specified time.
			List<Wastage> list1 = dataFromDB.stream()
					.filter(x -> (x.getWastageDate().isAfter(start_date) && x.getWastageDate().isBefore(closing_dat)
							|| x.getWastageDate().isEqual(start_date) || x.getWastageDate().equals(closing_dat)))
					.collect(Collectors.toList());

			
			for (Wastage p : list1) {
				
				// add price per unit in wastage_total
				wastage_total += p.getPricePerUnit() * p.getQty();
				
				// first convert wastage to wastageDto then add inside list
				list.add(wastmapper.convertToDto(p));
			}
			this.wastage_total = wastage_total;
		}
		} else {
			return list;
		}
		return list;
		}
		return list;

	}

	// ---------------------------------PurchaseOrderDto------------------------------------//
	@Override
	public List<purchaseOrderDto> getpurchaseOrder() {

		double purchase_total = 0;
		
		// create the purchaseOrderDto list
		List<purchaseOrderDto> pr = new ArrayList();

		// get all the data from database, so we can use for filteration.
		List<PurchaseOrder> purchase_ = purchaseRepo.findAll();
		List<Supplier> supplier_repo = supplierRepo.findAll();
		List<Invoice> dataFromDB = invoicerepo.findAll();

		PurchaseOrder first_purchase;
        // It return the set the first purchaseOrder object exported data.
		if(closerepo.findAll().isEmpty()) {
			
			// It return the first purchase object
		    first_purchase = purchaseRepo.getLastSession("exported");
		}
		else {
			// It return the first purchase object with exported status but it should be after the last closed end date
			first_purchase = purchaseRepo.getStartSessionDate("exported", closerepo.OpeningDate().getStock_end_date());
			
		}
		
		
		// last purchase should not be null
		if(first_purchase != null)
		{
			
		// get the last purchase order date
		LocalDate session_start = first_purchase.getPurchaseOrderDate();
		
		// set the starting and closing date
		LocalDate closing_dat = stock_close_date != null ? stock_close_date : LocalDate.now();
		LocalDate start_date = stock_start_date != null ? stock_start_date : session_start;


		this.stock_period_start = start_date;

//		

		// use predicate so to check this month data is available or not
		Predicate<PurchaseOrder> s1 = s -> s.getPurchaseOrderDate().isAfter(start_date)
				|| s.getPurchaseOrderDate().isEqual(start_date);
		boolean isPresent = purchase_.stream().anyMatch(s1);

		// if this month data is available then it allow the program to enter the if
		// condition
		if (isPresent) {

			// Here we filter the data based on date
			List<PurchaseOrder> purchase = purchase_.stream().filter(
					x -> (x.getPurchaseOrderDate().isAfter(start_date) && x.getPurchaseOrderDate().isBefore(closing_dat) 
							|| x.getPurchaseOrderDate().isEqual(start_date))
							|| x.getPurchaseOrderDate().equals(closing_dat))
					.collect(Collectors.toList());

			for (int i = 0; i < purchase.size(); i++) {
				
				purchaseOrderDto pur = new purchaseOrderDto();
				String invoice_status;
				int a = i;

				// it verify the purchasedID is present or not inside Invoice entity
				boolean result1 = invoicerepo.existsById(purchase.get(i).getPurchasedId());

				// It return Attached if Invoice is generated else Not Attached
				if (result1) {
					invoice_status = "Attacted";
				} else {
					invoice_status = "Not Attached";
				}
				// add total value
				purchase_total += purchase.get(i).getTotalAmount();
				pur.setPurchasedId(purchase.get(i).getPurchasedId());
				pur.setPurchaseOrderDate(purchase.get(i).getPurchaseOrderDate());
				pur.setTotalAmount(purchase.get(i).getTotalAmount());
				pur.setPurchaseOrderStatus(purchase.get(i).getPurchaseOrderStatus());
				pur.setInvoice_status(invoice_status);
				UUID prr = purchase.get(a).getPurchasedId();

				// get supplier name of each purchase id
				String supplier_name = purchase.get(a).getSupplier().getSupplierBasic().getSupplierBusinessName();
				pur.setSupplierBusinessName(supplier_name);
				// add in list
				pr.add(pur);
			}
			this.purchase_total = purchase_total;
		} else {
			return pr;
		}
		return pr;
		}
		return pr;
	}
	

	// ---------------------------Stock Balance-------------------//
	@Override
	public List<StockBalanceDto> getStockBalance() throws InventoryException {

		double stock_total = 0;
		// get all product data
		List<Product> list = repo.findAll();

		// create the stock list
		List<StockBalanceDto> stock_list = new ArrayList();

		boolean b = list.isEmpty();

		// check list should not be null
		if (list.size() != 0) {
 
			// get the last stored data from close stock 
			CloseStock dataFrom = closerepo.OpeningDate();

			// declaration of opening and closing date
			LocalDate opening_date, opening_close_date;

			// check closerepo should not be null
			if (closerepo.findAll().isEmpty() == false) {

				opening_date = dataFrom.getStock_start_date();
				opening_close_date = dataFrom.getStock_end_date();

			} else {
				opening_date = repo.findAll().get(0).getProductEffectiveDate();
				opening_close_date = LocalDate.now();
			}
			
			
			
			

			// It is use to filter the data between given date
			List<OpeningStockDto> list1 = getOpenStock();
			
			

			// set the start and close date
			LocalDate closing_dat = stock_close_date != null ? stock_close_date : opening_close_date;
			LocalDate start_date = stock_start_date != null ? stock_start_date : opening_date;

			List<Product> product = list.stream()
					.filter(x -> (x.getProductEffectiveDate().isAfter(start_date)
							&& x.getProductEffectiveDate().isBefore(closing_dat)
							|| x.getProductEffectiveDate().isEqual(start_date)
							|| x.getProductEffectiveDate().equals(closing_dat)))
					.collect(Collectors.toList());
			

			// get list of wastage from db
			List<Wastage> wast_list = wsrepo.findAll();
			
			

			for (Product l : product) {

				StockBalanceDto stock = new StockBalanceDto();
				// assign stock_balance values for each object using this foreach loop
				stock.setProductCode(l.getProductCode());
				stock.setProductName(l.getProductName());
				stock.setOrdered_qty(l.getQty());
                
				// filter the wastage list based on product code , product code of wastage list
				// should be same as product table
				List<Integer> stock_qty = wast_list.stream().filter(x -> x.getProductCode() == l.getProductCode())
						.map(x -> x.getQty()).collect(Collectors.toList());
				
				// set wastage qty
				int ws_count;
				
				if(stock_qty.isEmpty()) {
					ws_count=0;
					stock.setWastage_qty(0);
				}else {
					ws_count=stock_qty.get(0);
					stock.setWastage_qty(stock_qty.get(0));
				}
				
				
				List<Integer> openingStock = list1.stream().filter(x -> x.getProductCode() == l.getProductCode())
						.map(x -> x.getQty()).collect(Collectors.toList());
				System.out.println(openingStock+" "+l.getProductCode());
				
				int open_count;
				if(openingStock.isEmpty()) {
					open_count=0;
					stock.setOpening_qty(0);
				}
				else {
					open_count=openingStock.get(0);
					stock.setOpening_qty(openingStock.get(0));
				}
				// total number of quantity
				long total = l.getQty() - ws_count+open_count;
				// stock total calculation
				stock_total = (l.getQty() - ws_count+open_count) * l.getPricePerUnit();
				stock.setTotal(total);
				stock_list.add(stock);
				
			}
			// assign stock balance total
			this.stock_balance_total = stock_total;
			// return stock list
		}
		return stock_list;

	}

	// -------------------------------- Summary-----------------------//
	@Override
	public Summary getSummary() throws InventoryException {

		// create a object of summary
		LocalDate session_start = stock_start_date != null ? stock_start_date : get_stock_start_date();   
		LocalDate session_end = stock_close_date != null ? stock_close_date : null;
//		stock_start_date = null;
//		stock_close_date = null;

		// closing stock values
		double closing_value = closing_stock_values != 0 ? closing_stock_values : 0;
		closing_stock_values = 0;

		// opening stock values
		double opening_values = opening_stock_values != 0 ? opening_stock_values : getTotal();
		opening_stock_values = 0;

		double cost_of_sales = this.cost_of_sales != 0 ? this.cost_of_sales : 0;
		this.cost_of_sales = 0;

		Summary su = new Summary();

		// set all values using getter and setter
		su.setOpening_stock_value(opening_values);
		su.setTotal_purchases(getPurchaseTotal());
		su.setWastage(getwastageTotal());

		// su.setNet_sales(getSales());
		su.setStock_period_start_date(session_start);
		su.setStock_period_end_date(session_end);
		su.setActual_cost_of_sales(cost_of_sales);
		su.setClosing_stock_value(closing_value);

		// return summary
		return su;
	}

//-----------------------It return total value of open stock--------------//
	@Override
	public Double getTotal() throws InventoryException {

		getOpenStock();
		return open_total;
	}

//----------------------It return purchase total value of purchase stock------------//
	@Override
	public Double getPurchaseTotal() throws InventoryException {

		getpurchaseOrder();
		return purchase_total;
	}

//----------------------It return wastage total value-----------------------------//
	@Override
	public Double getwastageTotal() throws InventoryException {

		showWastage();
		return wastage_total;
	}

//--------------------- Net Sales ---------------------------------------//
	@Override
	public Double getSales() throws InventoryException {

		net_sales = getPurchaseTotal() - getTotal();
		return net_sales;
	}

//----------------------stock balance total value -------------------------//
	@Override
	public Double get_stock_balance_total() throws InventoryException {

		// call this method to get stock balance total
		getStockBalance();
		// return stock balance total
		return stock_balance_total;
	}

//------------------------Start Date -----------------------------------------//

	public LocalDate get_stock_start_date() throws InventoryException {

		getpurchaseOrder();
		
		if(stock_period_start ==null)
		{
			return null;
		}
		
		return stock_period_start ;
	}

//----------------------- view stock management page -------------------------------------//

	@Override
	public List<CloseStock> get_view_close_stock() throws InventoryException {

		// get all data from close repository
		List<CloseStock> dataFromDB = closerepo.findAll();

		if (dataFromDB.size() == 0) {
			return new ArrayList();
		}

		List<CloseStockDto> closeDto_List = new ArrayList();
		CloseStockDto close_stock_dto = new CloseStockDto();
		if (dataFromDB.size() == 0) {
			throw new InventoryException("No data available!!!");
		}
		// return List of close stock data
		for (CloseStock s : dataFromDB) {
			close_stock_dto.setStockID(s.getStockID());
			close_stock_dto.setStock_start_date(stock_start_date);
			close_stock_dto.setStock_end_date(stock_close_date);
			close_stock_dto.setOpening_stock_value(open_total);
			close_stock_dto.setClosing_stock_value(closeValuesImpl.closing_value);
//			close_stock_dto.setCost_of_sales(net_sales);
			closeDto_List.add(close_stock_dto);
		}

		return dataFromDB;
	}

//------------------------add close stock ---------------------------------------//
	@Override
	public String add_close_stock(String status) throws InventoryException {

		// create a date
		LocalDate close_date = LocalDate.now();

		// create object of closeStock
		CloseStock close_stock = new CloseStock();

		// cost of sales
		double cost_of_sales = getTotal() + getPurchaseTotal() - getwastageTotal() - closeValuesImpl.closing_value;

		// assign values
		close_stock.setStock_start_date(get_stock_start_date());
		close_stock.setStock_end_date(close_date);
		close_stock.setOpening_stock_value(getTotal());
		close_stock.setStockPeriodStatus(StockStatus.valueOf(status));
		close_stock.setClosing_stock_value(closeValuesImpl.closing_value);
		close_stock.setCost_of_sales(cost_of_sales);
		closeValuesImpl.closing_value = 0;
		this.open_total = 0;
		// save the close_stock object to close repository
		closerepo.save(close_stock);

		// return successfull message
		return "Added Successfully";
	}

//---------------------It return the details view of close stock-------------------//
	@Override
	public CloseStockDetailDto view_stock_detail_date(UUID stockID) throws InventoryException {

		// access data from database by date
		Optional<CloseStock> dateFromDB = closerepo.findById(stockID);
		CloseStockDetailDto close_detail = new CloseStockDetailDto();
		
		if(dateFromDB.isPresent()) {
		
		// assign stock_close_date
		this.stock_close_date = dateFromDB.get().getStock_end_date();

		this.stock_start_date = dateFromDB.get().getStock_start_date();
		
		

		// set closing stock value
		this.closing_stock_values = dateFromDB.get().getClosing_stock_value();
		// set opening stock values
		this.opening_stock_values = dateFromDB.get().getOpening_stock_value();

		// set cost of sales
		this.cost_of_sales = dateFromDB.get().getCost_of_sales();

		// create a object of close_stock_details_dto
		

		// using getter and setter assign values in close_detail
		close_detail.setOpen_stock(getOpenStock());
		close_detail.setPurchase_order(getpurchaseOrder());
		close_detail.setStock_balance(getStockBalance());
		close_detail.setSummary(getSummary());
		close_detail.setWastage(showWastage());
		// close_detail.setSite_transfer(site_transfer.show);

		return close_detail;
		}
		return close_detail;
	}

//--------------------------Approve a stock period for a closed stock--------------//
	@Override
	@Modifying
	public String approved_stock_period(UUID stockID) throws InventoryException {

		// access data from database by date
		Optional<CloseStock> dataFromDB = closerepo.findById(stockID);

		
		if(dataFromDB.isPresent()) {
		
		// set status to approved
		dataFromDB.get().setStockPeriodStatus(StockStatus.approved);
		// save the change
		closerepo.save(dataFromDB.get());
		// return message of approved.
		return "Approved";
	}
		return "Sorry id not found";
	}

//--------------------------Bulk of Approval from view screen --------------------------------//

	@Override
	public String bulk_approved_stock_period(List<CloseStockDto> bulk_approval) throws InventoryException {


		for (CloseStockDto s : bulk_approval) {

			Optional<CloseStock> stock = closerepo.findById(s.getStockID());

			if(stock.isPresent()) {
			
			stock.get().setStockPeriodStatus(StockStatus.approved);

			closerepo.save(stock.get());


		}
		}
		return "Approved All";
	}

	@Override
	public String back_real_time() {
		// TODO Auto-generated method stub
		this.stock_start_date=null;
		this.stock_close_date=null;
		return "Done";
	}



	

}
