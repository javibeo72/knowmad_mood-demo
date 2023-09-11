package com.test.knowmadmod.pricecatalog.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.knowmadmod.pricecatalog.dto.RequestProductDto;
import com.test.knowmadmod.pricecatalog.dto.ResponseProductDetailDto;
import com.test.knowmadmod.pricecatalog.model.PriceCatalog;
import com.test.knowmadmod.pricecatalog.repository.PriceCatalogRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PriceCatalogServiceImpl implements PriceCatalogService {

	@Autowired
	PriceCatalogRepository priceCatalogRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Optional<ResponseProductDetailDto> getProductDetail(RequestProductDto request) {
		
		log.info(String.format("KNOWMAD_MOOD: Retrieve all the available fares for product id [%d] , brand id [%d] at application date [%s]. "
							   ,request.getProductId(), request.getBrandId(), request.getApplicationDate().toString()));
		
		List<PriceCatalog> listOfPrices = getListOfProductFares(request);
		
		if (listOfPrices.size() == 0) {
			log.error("KNOWMAD_MOOD: No list of fares available for the product requested at the application date.");
		} else if (listOfPrices.size() == 1) {
			log.debug("KNOWMAD_MOOD: One fare available.");
		} else {
			log.debug("KNOWMAD_MOOD: Several fares available, it will be select the one with highest priority.");
		}
		
		return listOfPrices.stream().max(Comparator.comparing(PriceCatalog::getPriority)).map(this::convertToDto);
		
	}
	
	private List<PriceCatalog> getListOfProductFares(RequestProductDto request) {
		return priceCatalogRepository.findListOfPricesByProduct(request.getProductId(), request.getBrandId(), request.getApplicationDate());
	}
	
	private ResponseProductDetailDto convertToDto(PriceCatalog priceCatalog) {
		ResponseProductDetailDto responseProductDetailDto = modelMapper.map(priceCatalog, ResponseProductDetailDto.class);
	    return responseProductDetailDto;
	}

}
