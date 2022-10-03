package com.ros.inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ros.inventory.controller.dto.OpeningStockDto;
import com.ros.inventory.controller.dto.purchaseOrderDto;
import com.ros.inventory.controller.dto.wastageDto;
import com.ros.inventory.entities.OpeningStock;
import com.ros.inventory.entities.Product;
import com.ros.inventory.mapper.AddProductMapper;
import com.ros.inventory.mapper.AddProductMapperImpl;
import com.ros.inventory.mapper.ApprovedMapper;
import com.ros.inventory.mapper.ApprovedMapperImpl;
import com.ros.inventory.mapper.ApprovedViewMapper;
import com.ros.inventory.mapper.ApprovedViewMapperImpl;
import com.ros.inventory.mapper.CloseStockDtoMapper;
import com.ros.inventory.mapper.CloseStockDtoMapperImpl;

import com.ros.inventory.mapper.AttachmentsMapper;
import com.ros.inventory.mapper.AttachmentsMapperImpl;

import com.ros.inventory.mapper.ClosingValueMapper;
import com.ros.inventory.mapper.ClosingValueMapperImpl;

import com.ros.inventory.mapper.DeliverMapper;
import com.ros.inventory.mapper.DeliverMapperImpl;
import com.ros.inventory.mapper.InvoiceMapper;
//import com.ros.inventory.mapper.InvoiceMapper2;
//import com.ros.inventory.mapper.InvoiceMapper2Impl;
import com.ros.inventory.mapper.InvoiceMapperImpl;
import com.ros.inventory.mapper.InvoicePMapper;
import com.ros.inventory.mapper.InvoicePMapperImpl;
import com.ros.inventory.mapper.OpenStockMapper;
import com.ros.inventory.mapper.OpenStockMapperImpl;
import com.ros.inventory.mapper.ProductMapper;
import com.ros.inventory.mapper.ProductMapperImpl;
import com.ros.inventory.mapper.ProductPMapper;
import com.ros.inventory.mapper.ProductPMapperImpl;
import com.ros.inventory.mapper.PurchaseOrderDtoMapper;
import com.ros.inventory.mapper.PurchaseOrderDtoMapperImpl;
import com.ros.inventory.mapper.PurchaseOrderMapper;
import com.ros.inventory.mapper.PurchaseOrderMapperImpl;
import com.ros.inventory.mapper.RejectedProductMapper;
import com.ros.inventory.mapper.RejectedProductMapperImpl;
import com.ros.inventory.mapper.SiteTransferMapper;
import com.ros.inventory.mapper.SiteTransferMapperImpl;
import com.ros.inventory.mapper.StockPeriodMapper;
import com.ros.inventory.mapper.StockPeriodMapperImpl;
import com.ros.inventory.mapper.SupplierDescriptionMapper;
import com.ros.inventory.mapper.SupplierDescriptionMapperImpl;
import com.ros.inventory.mapper.SupplierMapper;
import com.ros.inventory.mapper.SupplierMapperImpl;
import com.ros.inventory.mapper.wastageDtoMapper;
import com.ros.inventory.mapper.wastageDtoMapperImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
public class InventoryStockServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventoryStockServiceApplication.class, args);

	}
	
	@Value(value = "${swagger.url}")
	public String url;
	
	@Bean
	public OpenAPI customOpenAPI() {
		Server server = new Server();
		List<Server> servers = new ArrayList<>();
		server.setUrl(url);
		servers.add(server);
		OpenAPI openAPI = new OpenAPI();
		openAPI.setServers(servers);
		return openAPI;
	}

	@Bean StockPeriodMapper getStockMapper() {
		return new StockPeriodMapperImpl();
	}

	@Bean
	public SupplierMapper mapper() {
		return new SupplierMapperImpl();
	}

	@Bean
	public SupplierDescriptionMapper emapper() {
		return new SupplierDescriptionMapperImpl();
	}

	@Bean
	public AddProductMapper amapper() {
		return new AddProductMapperImpl();
	}

	@Bean
	public ProductMapper pmapper() {
		return new ProductMapperImpl();
	}

	@Bean
	public ProductPMapper promapper() {
		return new ProductPMapperImpl();
	}

	@Bean
	public ApprovedMapper apMapper() {
		return new ApprovedMapperImpl();
	}

	@Bean
	public RejectedProductMapper rpMapper() {
		return new RejectedProductMapperImpl();
	}

	@Bean
	public SiteTransferMapper spMapper() {
		return new SiteTransferMapperImpl();
	}

	@Bean
	public ApprovedViewMapper avMapper() {
		return new ApprovedViewMapperImpl();
	}

	@Bean
	public DeliverMapper dMapper() {
		return new DeliverMapperImpl();
	}

	@Bean
	public InvoicePMapper iMapper() {
		return new InvoicePMapperImpl();
	}

	@Bean
	public InvoiceMapper imapper() {
		return new InvoiceMapperImpl();
	}

	
	@Bean
	public wastageDtoMapper wasMapper() {
		return new wastageDtoMapperImpl();
	}

	@Bean
	public PurchaseOrderMapper pmapper1() {
		return new PurchaseOrderMapperImpl();
	}


	@Bean
	public OpeningStock op_stock() {
		return new OpeningStock();
	}

	@Bean
	public Product pro_closing() {
		return new Product();
	}


	@Bean
	public ClosingValueMapper clmapper() {
		return new ClosingValueMapperImpl();
	}


	@Bean
	public PurchaseOrderDtoMapper purmapper() {
		return new PurchaseOrderDtoMapperImpl();
	}

	@Bean
	public AttachmentsMapper aMapper() {
		return new AttachmentsMapperImpl();
	}

	@Bean
	public OpeningStockDto osd() {
		return new OpeningStockDto();
	}

	@Bean
	public purchaseOrderDto pur() {
		return new purchaseOrderDto();
	}

	@Bean
	public wastageDto wdto() {
		return new wastageDto();
	}

	@Bean
	public CloseStockDtoMapper csdm() {

		return new CloseStockDtoMapperImpl();
	}
	
	@Bean
	public OpenStockMapper opdm() {

		return new OpenStockMapperImpl();
	}


}
