package com.ros.inventory.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.Invoice;
//import com.ros.inventory.entities.Product;
@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice, UUID> {
	//@Query(value = "SELECT * FROM invoice"
		//	+ " WHERE invoiceId =:invoiceId", nativeQuery = true)
	//List<Invoice> getAll(@Param("invoiceId") long id);
	//@Query(value = "SELECT * FROM Invoice i INNER JOIN ProductOrder p on i.invoiceId=p.purchase_id", nativeQuery = true)
	//List<Invoice> getAll();

	@Query(value = "SELECT * FROM invoice"
			+ " WHERE invoiceId =:invoiceId", nativeQuery = true)
	Invoice getById(@Param("invoiceId") UUID id);
	
	

}
