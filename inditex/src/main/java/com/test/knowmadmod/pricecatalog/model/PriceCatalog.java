package com.test.knowmadmod.pricecatalog.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PRICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceCatalog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	@Column(name = "BRAND_ID")
	private Long brandId;
	
	@Column(name = "START_DATE")
	private LocalDateTime startDate;
	
	@Column(name = "END_DATE")
	private LocalDateTime endDate;
	
	@Column(name = "PRICE_LIST")
	private Integer priceList;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "PRIORITY")
	private Integer priority;
	
	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "CURR")	
	private String currency;

}
