package com.example.productentry.util;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

public class ProductEntryValidation {

	public void validateRequest(@RequestBody Map<String, String> req) throws Exception {
		if(req.get("productname") == null || req.get("stock") == null || req.get("offer") == null || req.get("price") == null){
			throw new Exception("input value cannot be null");
		} else if(req.get("productname").equals("") || req.get("stock").equals("") || req.get("offer").equals("") || req.get("price").equals("")) {
			throw new Exception("input value cannot be empty");
		}
	}
}
