package com.example.demo;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class CartController {

	@Autowired
	private CartServices cartServices;
	
	@Autowired
	private CustomerUserDetailsService userServices;
	
	@Autowired
	private OrdersRepository orderRepo;
	
	
	@GetMapping("/Cart")
	public String showCart(Model model , @AuthenticationPrincipal Authentication authentication){
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userServices.getCurrentlyLoggedInCustomer(authentication);
		List<Cart> cartItems = cartServices.listCartItems(user);
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("pageTitle", "Shopping Cart");
		model.addAttribute("orders", new Orders());
		return "Cart";
	}
	
	@PostMapping("/checkout")
	public String checkout(Orders order) {
		//-------------
		System.out.println(order.getCustomer_name()+order.getMenu_list()+order.getTotal_amount());
		orderRepo.save(order);
		return "checkout";
	}

	
}
