package com.test.knowmadmod.pricecatalog.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestProductDto {
	
	@NotNull(message = "The application date is required.")
	
	@JsonFormat(pattern = "yyyy-MM-dd HH.mm.ss")
	private LocalDateTime applicationDate;
	
	@NotNull(message = "The product is required.")
	@Min(value = 1L, message = "The product id must be positive")
	private Long productId;
	
	@NotNull(message = "The brand id required.")
	@Min(value = 1L, message = "The brand id must be positive")
	private Long brandId;

}
