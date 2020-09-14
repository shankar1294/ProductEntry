package com.example.productentry.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productentry.model.Products;
import com.example.productentry.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProductEntryService {

	@Autowired
	ProductRepository productRepository;
	
	public Products createProduct(Map<String, String> req) {

		Products product = new Products();
		product.setProductname(req.get("productname"));
		product.setStock(Integer.parseInt(req.get("stock")));
		product.setPrice(Double.parseDouble(req.get("price")));
		product.setOffer(Integer.parseInt(req.get("offer")));

		return productRepository.save(product);

	}
	
	public Products updateProduct(String id, Map<String, String> body) {

		int productId = Integer.parseInt(id);
		log.trace("productId :::::"+productId);
		Products product = null;
		Optional<Products> products = productRepository.findById(productId);
		if(products.isPresent()) {
			product  = products.get();
			product.setProductname(body.get("productname"));
			product.setStock(Integer.parseInt(body.get("stock")));
			product.setPrice(Double.parseDouble(body.get("price")));
			product.setOffer(Integer.parseInt(body.get("offer")));        
		}
		return productRepository.save(product);

	}

	public List<Products> getAllProduct() throws Exception{		
		return productRepository.findAll();	
	}
	
	public Products getById(String id) {
		Optional<Products> products = productRepository.findById(Integer.parseInt(id));
		if(products.isPresent()) {
			return products.get();
		}
		return null;
	}

}
