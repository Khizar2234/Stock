package com.ros.inventory.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.ros.inventory.Exception.InventoryException;
import com.ros.inventory.Repository.ProductRepository;
import com.ros.inventory.Repository.SupplierRepository;
import com.ros.inventory.controller.dto.ProductDto;
import com.ros.inventory.controller.dto.ProductPDto;
import com.ros.inventory.entities.Product;
import com.ros.inventory.entities.Supplier;
import com.ros.inventory.mapper.ProductMapper;
//import com.ros.inventory.mapper.ProductMapper;
import com.ros.inventory.mapper.ProductPMapper;
import com.ros.inventory.service.IProductManager;

@Service
public class ProductManager implements IProductManager {
	@Autowired
	ProductRepository productRepo;

	@Autowired
	ProductMapper mapper;

	@Autowired
	SupplierRepository suprepo;

	// FOR DISPLAYING THE LIST OF PRODUCT THAT PARTICULAR SUPPLIER HAS

	@Override
	public List<Product> getProduct(UUID id) throws InventoryException {

		List<Product> productFromDB = productRepo.getAll(id);

		if (productFromDB == null || productFromDB.size() == 0) {
			throw new InventoryException("No Product is available");
		}

		return productFromDB;
	}

	/*--------------------FOR DELETING THE PARTICULAR ITEM -----------------------------*/
	@Override
	public Product delete(UUID id) throws InventoryException {

		Product productFromDB = productRepo.getById(id);

		if (productFromDB == null) {
			throw new InventoryException("product not exist");
		} else {
			productRepo.deleteById(id);
		}

		return productFromDB;
	}

	/*-----------------FOR UPDATING THE PARTICULAR PRODUCT ITEM  ------------------------------- */
	@Override
	public Product update(Product product) throws InventoryException {

		return productRepo.saveAndFlush(product);
	}

	@Override
	public List<ProductDto> getByName(String bname) {

		List<Product> list = productRepo.findAll();

		List<Supplier> supplier = suprepo.findAll();

		List<UUID> supplier_id = supplier.stream()
				.filter(x -> x.getSupplierBasic().getSupplierBusinessName().equals(bname)).map(x -> x.getSupplierId())
				.collect(Collectors.toList());

		List<ProductDto> list1 = new ArrayList();

		List<List<Product>> products = supplier.stream().filter(x -> x.getSupplierId() == supplier_id.get(0))
				.map(x -> x.getProducts()).collect(Collectors.toList());

		for (List<Product> p : products) {

			for (Product s : p) {

				list1.add(mapper.convertToDto(s));
			}

		}
		return list1;
	}

	@Override
	@Modifying
	public String updateProduct(List<ProductPDto> pr) {

		for (ProductPDto p : pr) {
			Product data = productRepo.getByIDCode(p.getProductCode());
			data.setQty(p.getQty());
			productRepo.save(data);

		}
		return "Updated";
	}

	/* ............Updating the Product Price....... */
	@Override
	public String updateProductPrice(List<ProductPDto> price) throws InventoryException {

		for (ProductPDto p : price) {
			Product data = productRepo.getByIDCode(p.getProductCode());
			data.setPricePerUnit(p.getPricePerUnit());
			productRepo.save(data);

		}
		return "Updated";
	}

}
