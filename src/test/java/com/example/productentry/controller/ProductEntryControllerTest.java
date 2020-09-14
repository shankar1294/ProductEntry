package com.example.productentry.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.productentry.model.Products;
import com.example.productentry.service.ProductEntryService;
import com.example.productentry.util.ProductEntryValidation;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductEntryController.class)
@WithMockUser
class ProductEntryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductEntryService productEntryService;

	@Test
	void createProduct() throws Exception {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "baskets");
		mockReq.put("stock" , "20");
		mockReq.put("price" , "15.0");
		mockReq.put("offer" , "10");

		Products mockresp = new Products();
		mockresp.setId(1);
		mockresp.setOffer(10);
		mockresp.setPrice(15.00);
		mockresp.setProductname("baskets");
		mockresp.setStock(20);

		String exampleCourseJson = "{\"productname\":\"baskets\",\"stock\":\"20\",\"price\":\"15.00\",\"offer\":\"10\"}";
		Mockito.when(
				productEntryService.createProduct(mockReq)).thenReturn(mockresp);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/product")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	void updateProduct() throws Exception {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "baskets");
		mockReq.put("stock" , "20");
		mockReq.put("price" , "15.0");
		mockReq.put("offer" , "10");

		Products mockresp = new Products();
		mockresp.setId(1);
		mockresp.setOffer(10);
		mockresp.setPrice(15.00);
		mockresp.setProductname("baskets");
		mockresp.setStock(20);

		String exampleCourseJson = "{\"productname\":\"baskets\",\"stock\":\"20\",\"price\":\"15.00\",\"offer\":\"10\"}";
		Mockito.when(
				productEntryService.updateProduct("1", mockReq)).thenReturn(mockresp);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/product/1")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertNotNull(result.getResponse());

	}

	@Test
	void getAllProducts()  throws Exception {
		List<Products> resp = new ArrayList<Products>();
		
		Products mockresp = new Products();
		mockresp.setId(1);
		mockresp.setOffer(10);
		mockresp.setPrice(15.00);
		mockresp.setProductname("baskets");
		mockresp.setStock(20);
		resp.add(mockresp);
		
		Mockito.when(
				productEntryService.getAllProduct()).thenReturn(resp);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/all-products")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertNotNull(result.getResponse());
		
	}
	
	@Test
	void getProductsById()  throws Exception {
		
		Products mockresp = new Products();
		mockresp.setId(1);
		mockresp.setOffer(10);
		mockresp.setPrice(15.00);
		mockresp.setProductname("baskets");
		mockresp.setStock(20);
		
		Mockito.when(
				productEntryService.getById("1")).thenReturn(mockresp);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/product/1")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertNotNull(result.getResponse());
		
	}
	
	@Test
	void checkProductNull() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , null);
		mockReq.put("stock" , "20");
		mockReq.put("price" , "15.0");
		mockReq.put("offer" , "10");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkStockNull() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "ball");
		mockReq.put("stock" , null);
		mockReq.put("price" , "15.0");
		mockReq.put("offer" , "10");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkPriceNull() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "ball");
		mockReq.put("stock" , "10");
		mockReq.put("price" , null);
		mockReq.put("offer" , "10");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkOfferNull() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "ball");
		mockReq.put("stock" , "10");
		mockReq.put("price" , "23");
		mockReq.put("offer" , null);

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkProductEmpty() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "");
		mockReq.put("stock" , "10");
		mockReq.put("price" , "23");
		mockReq.put("offer" , "23");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkStockEmpty() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "ball");
		mockReq.put("stock" , "");
		mockReq.put("price" , "23");
		mockReq.put("offer" , "23");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkPriceEmpty() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "ball");
		mockReq.put("stock" , "5");
		mockReq.put("price" , "");
		mockReq.put("offer" , "23");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}
	
	@Test
	void checkOfferEmpty() {

		Map<String, String> mockReq = new HashMap<String, String>();

		mockReq.put("productname" , "ball");
		mockReq.put("stock" , "5");
		mockReq.put("price" , "23");
		mockReq.put("offer" , "");

		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		Throwable e = null;
		try {
			productEntryValidation.validateRequest(mockReq);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof Exception);		
	}

}
