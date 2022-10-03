package com.ros.inventory.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.inventory.entities.Wastage;

@Repository
public interface WastageRepository extends JpaRepository<Wastage,UUID>
{
	@Query(value="SELECT * FROM wastage WHERE product_name=:product_name",nativeQuery=true)
	   Wastage byName(@Param("product_name") String name);
	   
	@Query(value="SELECT * FROM wastage WHERE product_code=:product_code",nativeQuery=true)
	   Wastage byCode(@Param("product_code") long code);

	@Query(value="SELECT * FROM wastage WHERE wastage_id = :wastage_id",nativeQuery=true)
	   Wastage byId(@Param("wastage_id") UUID id);
}
