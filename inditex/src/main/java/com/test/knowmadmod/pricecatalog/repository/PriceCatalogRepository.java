package com.test.knowmadmod.pricecatalog.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.knowmadmod.pricecatalog.model.PriceCatalog;

@Repository
public interface PriceCatalogRepository extends JpaRepository<PriceCatalog, String>{
	
	List<PriceCatalog> findByProductId(Long productId);

	@Query("select p from PriceCatalog p"
			+ "     where p.productId = :productId "
			+ "       and p.brandId = :brandId"
			+ "       and p.startDate <= :applicationDate and p.endDate >= :applicationDate")
	List<PriceCatalog> findListOfPricesByProduct(@Param("productId") Long productId, @Param("brandId") Long BrandId,
												 @Param("applicationDate") LocalDateTime applicationDate);


	
	
	

}
