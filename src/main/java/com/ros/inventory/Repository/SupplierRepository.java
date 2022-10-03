package com.ros.inventory.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
	@Query(value = "SELECT * FROM supplier WHERE supplier_id =:supplier_id", nativeQuery = true)
	Supplier getById(@Param("supplier_id") UUID supplierId);

	@Query(value = "SELECT * FROM supplier", nativeQuery = true)
	List<Supplier> getAll();

	@Query(value = "SELECT * FROM supplier s LEFT JOIN supplier_basic_info sb on s.supplierbasic_id = sb.supplier_info_id"
			+ " where sb.supplier_bussiness_name= :supplier_bussiness_name ", nativeQuery = true)
	Supplier getByName(@Param("supplier_bussiness_name") String sName);
//for invoice.
	@Query(value = "SELECT * FROM supplier s LEFT JOIN puchase_order p on s.supplier_id = p.purchase_id"
			+ " where p.purchase_id=:purchase_id ", nativeQuery = true)
	Supplier getByParchase(@Param("purchase_id") UUID id);
}
