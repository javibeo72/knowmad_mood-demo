package com.test.knowmadmod.pricecatalog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.knowmadmod.pricecatalog.dto.RequestProductDto;
import com.test.knowmadmod.pricecatalog.dto.ResponseProductDetailDto;
import com.test.knowmadmod.pricecatalog.service.PriceCatalogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class PriceCatalogController {
	
	@Autowired
	PriceCatalogService priceCatalogService;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping()
	public ResponseEntity<?> getProductDetail(@Valid @RequestBody RequestProductDto request) {
		
		Optional<?> currentProductFare = priceCatalogService.getProductDetail(request);
		
		if (currentProductFare.isPresent() ) {
			return new ResponseEntity<Object>(currentProductFare,HttpStatus.OK);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("Message",messageSource.getMessage("validation.nodata.found", null, LocaleContextHolder.getLocale()));
			return  new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
		}
		
	}

}
