package com.test.knowmadmod.pricecatalog.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProductDetailDto {
	
	private Long productId;
	
	private Long brandId;
	
	private Integer priceList;
	
	private LocalDateTime startDate;
		
	private LocalDateTime endDate;
	
	@JsonIgnore
	private Integer priority;
	
	private BigDecimal price;
		
	private String currency;
	
}
