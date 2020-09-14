package com.example.productentry.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.productentry.model.Products;
import com.example.productentry.service.ProductEntryService;
import com.example.productentry.util.ProductEntryValidation;

@RestController
public class ProductEntryController {
	
	@Autowired
	ProductEntryService productEntryService;
	
	@PostMapping(value = "/product" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Products createProduct(@RequestBody Map<String, String> req ) throws Exception{
		
		ProductEntryValidation productEntryValidation = new ProductEntryValidation();
		productEntryValidation.validateRequest(req);
		return productEntryService.createProduct(req);
	}
	
	@PostMapping("/product-update/{id}")
    public Products update(@PathVariable String id, @RequestBody Map<String, String> body){
       
		return productEntryService.updateProduct(id, body);
    }
	
	@GetMapping("/all-products")
	public List<Products> getAllProduct() throws Exception{		
		return productEntryService.getAllProduct();	
	}
	
	@GetMapping("/getproduct/{id}")
	public Products getById(@PathVariable String id) {
		return productEntryService.getById(id);
		
	}

}
