package com.test.knowmadmod.pricecatalog;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.knowmadmod.pricecatalog.dto.RequestProductDto;
import com.test.knowmadmod.pricecatalog.model.PriceCatalog;
import com.test.knowmadmod.pricecatalog.repository.PriceCatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class PriceCatalogApplicationTests {
	
	@MockBean
	private PriceCatalogRepository repository;
	
	@Autowired	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void test1() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 14, 10, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                             .productId(productId)
				                                             .applicationDate(dateTime).build();
		
		when(repository.findListOfPricesByProduct(anyLong(), anyLong(), any(LocalDateTime.class)))
			.thenReturn(getMockData(request));
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value( "2020-12-31T23:59:59"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.5"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
		
	}	
	
	@Test
	public void test2() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 14, 16, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                             .productId(productId)
				                                             .applicationDate(dateTime).build();
		
		when(repository.findListOfPricesByProduct(anyLong(), anyLong(), any(LocalDateTime.class)))
			.thenReturn(getMockData(request));
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T15:00:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-14T18:30:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("25.45"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
		
	}	
	
	@Test
	public void test3() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 14, 21, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                             .productId(productId)
				                                             .applicationDate(dateTime).build();
		
		when(repository.findListOfPricesByProduct(anyLong(), anyLong(), any(LocalDateTime.class)))
			.thenReturn(getMockData(request));
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value( "2020-12-31T23:59:59"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("35.5"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
		
	}	
	
	@Test
	public void test4() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 15, 10, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                             .productId(productId)
				                                             .applicationDate(dateTime).build();
		
		when(repository.findListOfPricesByProduct(anyLong(), anyLong(), any(LocalDateTime.class)))
			.thenReturn(getMockData(request));
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T00:00:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-15T11:00:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("30.5"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
		
	}
	
	@Test
	public void test5() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 16, 21, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                             .productId(productId)
				                                             .applicationDate(dateTime).build();
		
		when(repository.findListOfPricesByProduct(anyLong(), anyLong(), any(LocalDateTime.class)))
			.thenReturn(getMockData(request));
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(35455))
				.andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(4))
				.andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T16:00:00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value( "2020-12-31T23:59:59"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value("38.95"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
		
	}
	
	@Test
	public void testNoResultFoundWhenSearching() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 11, 19, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                             .productId(productId)
				                                             .applicationDate(dateTime).build();
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.Message")
						.value("There is no result using the current information provided."));
		
	}	
	
	
	@Test
	public void testBrandIdMustBeInformed() throws Exception {
		
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 16, 21, 0, 0);
		
		RequestProductDto test1 = RequestProductDto.builder().productId(productId)
				                                             .applicationDate(dateTime).build();
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(test1))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['Error Messages'].brandId")
						                                    .value("The brand id required."));
		
	}	
	
	@Test
	public void testBrandIdMustBeNumeric() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 24, 0, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
														       .productId(productId)
				                                               .applicationDate(dateTime).build();
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request).replaceAll("1", "\"XXX\""))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['Error Messages'].Description")
						                                    .value("Review numeric fields. Some value is not correct"));
		
	}	
	
	@Test
	public void testProductIdMustBeInformed() throws Exception {
		
		Long brandId = Long.valueOf(1);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 14, 21, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
				                                               .applicationDate(dateTime).build();
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['Error Messages'].productId")
						                                    .value("The product is required."));
		
	}	
	
	@Test
	public void testProductIdMustBeNumeric() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
														       .productId(productId)
				                                               .applicationDate(dateTime).build();
				
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request).replaceAll("35455", "\"XXX\""))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['Error Messages'].Description")
						                                    .value("Review numeric fields. Some value is not correct"));
		
	}	
	
	@Test
	public void testApplicationDateMustBeInformed() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
															   .productId(productId).build();
		
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['Error Messages'].applicationDate")
						                                    .value("The application date is required."));
		
	}	
	
	@Test
	public void testApplicationDateMustBeMustBeWellFormatted() throws Exception {
		
		Long brandId = Long.valueOf(1);
		Long productId = Long.valueOf(35455);
		LocalDateTime dateTime = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
		
		RequestProductDto request = RequestProductDto.builder().brandId(brandId)
														       .productId(productId)
				                                               .applicationDate(dateTime).build();
				
		mockMvc.perform(get("/api/v1/product")  
				.content(getJsonRequest(request).replaceAll("2020-06-14", "2020-06-"))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['Error Messages'].Description")
						.value("Review date-time fields. The format used should be YYYY-MM-dd HH.mm.ss"));
		
	}	
	
	
	private String getJsonRequest(RequestProductDto request) throws JsonProcessingException {

		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		
		return objectMapper.writeValueAsString(request);
		
	}
	
	private List<PriceCatalog> getMockData(RequestProductDto request) {
		
		List<PriceCatalog> listProductFares = new ArrayList<PriceCatalog>();
		
		PriceCatalog productPriceListOne = PriceCatalog.builder().brandId(Long.valueOf(1))
										 .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0))
										 .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
										 .priceList(1).productId(Long.valueOf(35455))
										 .priority(0).price(new BigDecimal("35.50")).currency("EUR").build();
		
		listProductFares.add(productPriceListOne);

		PriceCatalog productPriceListTwo = PriceCatalog.builder().brandId(Long.valueOf(1))
										 .startDate(LocalDateTime.of(2020, Month.JUNE, 14, 15, 0, 0))
										 .endDate(LocalDateTime.of(2020, Month.JUNE, 14, 18, 30, 0))
										 .priceList(2).productId(Long.valueOf(35455))
										 .priority(1).price(new BigDecimal("25.45")).currency("EUR").build();
		
		listProductFares.add(productPriceListTwo);
					
		PriceCatalog productPriceListThree = PriceCatalog.builder().brandId(Long.valueOf(1))
										   .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 0, 0, 0))
										   .endDate(LocalDateTime.of(2020, Month.JUNE, 15, 11, 0, 0))
										   .priceList(3).productId(Long.valueOf(35455))
										   .priority(1).price(new BigDecimal("30.50")).currency("EUR").build();
		
		listProductFares.add(productPriceListThree);
		
		PriceCatalog productPriceListFour = PriceCatalog.builder().brandId(Long.valueOf(1))
										  .startDate(LocalDateTime.of(2020, Month.JUNE, 15, 16, 0, 0))
										  .endDate(LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59))
										  .priceList(4).productId(Long.valueOf(35455))
										  .priority(1).price(new BigDecimal("38.95")).currency("EUR").build();
		
		listProductFares.add(productPriceListFour);
		
		Predicate <PriceCatalog> check1 = p -> p.getBrandId().equals(request.getBrandId());
		Predicate <PriceCatalog> check2 = p -> p.getProductId().equals(request.getProductId());
		Predicate <PriceCatalog> check3 = p -> p.getStartDate().isBefore(request.getApplicationDate());
		Predicate <PriceCatalog> check4 = p -> p.getEndDate().isAfter(request.getApplicationDate());
		
		Predicate <PriceCatalog> checkAll = check1.and(check2).and(check3).and(check4);
		
		return listProductFares.stream().filter(checkAll).collect(Collectors.toList());
		
	}
	
}
