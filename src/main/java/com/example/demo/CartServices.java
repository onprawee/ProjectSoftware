package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServices {

	@Autowired
	private CartRepository cartRepo;
	
	public List<Cart> listCartItems(User user){
		return cartRepo.findByUser(user);
	}
	
}
