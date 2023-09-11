package com.test.knowmadmod.pricecatalog.service;

import java.util.Optional;

import com.test.knowmadmod.pricecatalog.dto.RequestProductDto;
import com.test.knowmadmod.pricecatalog.dto.ResponseProductDetailDto;

public interface PriceCatalogService {
	public Optional<ResponseProductDetailDto> getProductDetail(RequestProductDto request);
}
