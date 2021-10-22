package com.example.demo;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

	@Autowired
	private CartServices cartServices;
	
	@Autowired
	private CustomerUserDetailsService userServices;
	
	@GetMapping("/Cart")
	public String showCart(Model model , @AuthenticationPrincipal Authentication authentication){
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userServices.getCurrentlyLoggedInCustomer(authentication);
		List<Cart> cartItems = cartServices.listCartItems(user);
	
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("pageTitle", "Shopping Cart");
		
		return "Cart";
	}

	
}
