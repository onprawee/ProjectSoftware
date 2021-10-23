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
	
	@PostMapping("/Cart/update/{pid}/{qty}")
	public String  updatequantity(@PathVariable("pid") Integer menuId, @PathVariable("qty") 
			Integer quantity, @AuthenticationPrincipal Authentication authentication) {
		//-------------
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userServices.getCurrentlyLoggedInCustomer(authentication);
		System.out.println("Quntity : " + menuId + "-" + quantity);
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must Login to update quantity.";
		}
		
		double subtotal = cartServices.updateQuantity(menuId, quantity, user);
		
		return String.valueOf(subtotal);
	}
	
	@PostMapping("/Cart/remove/{pid}")
	public String  removeMenufromCart(@PathVariable("pid") Integer menuId,
			@AuthenticationPrincipal Authentication authentication) {
		//-------------
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userServices.getCurrentlyLoggedInCustomer(authentication);
		
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must Login to remove menu.";
		}
		System.out.println("remove: " + 1 + "-" + user);
		cartServices.removeMenu(menuId,user);
		
		return "Menu has been removed from your Cart.";
	}
	
}

