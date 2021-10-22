package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestController {
	
	@Autowired
	private CartServices cartServices;
	
	@Autowired
	private CustomerUserDetailsService userServices;
	
	@PostMapping("/Cart/add/{pid}/{qty}")
	public String  addItemmstoCart(@PathVariable("pid") Integer menuId, @PathVariable("qty") 
			Integer quantity, @AuthenticationPrincipal Authentication authentication) {
		//-------------
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userServices.getCurrentlyLoggedInCustomer(authentication);
		System.out.println("add menuTocart : " + menuId + "-" + quantity);
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must Login to add this Mennu to your cart.";
		}
		
		Integer addQuantity = cartServices.addMenu(menuId, quantity, user);
		
		System.out.println("item added");
		
		return addQuantity + "item(s) of this menu ";
	}
	
}

