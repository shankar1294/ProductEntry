package com.example.userregistration.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userregistration.model.Product;
import com.example.userregistration.repository.ProductRepository;

@RestController
public class ProductEntryController {
	
	@Autowired
	ProductRepository userRepository;
	
	@PostMapping(value = "/user" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product createUser(@RequestBody Map<String, String> req ) throws Exception{
		
		Product user = new Product();
		if(req.get("firstname") == null || req.get("lastname") == null || req.get("email") == null) {
			throw new Exception("input value cannot be null");
		} else if(req.get("firstname") == "" || req.get("lastname") == "" || req.get("email") == "") {
			throw new Exception("input value cannot be empty");
		}
		
		user.setFirstname(req.get("firstname"));
		user.setLastname(req.get("lastname"));
		user.setEmail(req.get("email"));
		user.setReg_date(new Date(System.currentTimeMillis()));
	
		return userRepository.save(user);
	}
	
	
	@PostMapping("/user/{id}")
    public Product update(@PathVariable String id, @RequestBody Map<String, String> body){
        int userId = Integer.parseInt(id);
        
        Product user = null;
        Optional<Product> users = userRepository.findById(userId);
        if(users.isPresent()) {
        	user  = users.get();
        	user.setFirstname(body.get("firstname"));
    		user.setLastname(body.get("lastname"));
    		user.setEmail(body.get("email"));         
        }
        return userRepository.save(user);
    }

}
