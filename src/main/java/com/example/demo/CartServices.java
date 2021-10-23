package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServices {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired 
	private RecommendmenuRepository menuRepo;
	
	
	public List<Cart> listCartItems(User user){
		return cartRepo.findByUser(user);
	}
	
	public Integer addMenu (Integer menuId, Integer quantity, User user) { 
			Integer addQuantity = quantity;
	 
			RecommendMenu menu = menuRepo.findById(menuId).get();
		  
			Cart cartItem = cartRepo.findByUserAndRecommendmenu(user, menu);
		  
			if(cartItem != null) { 
				if (cartItem.getQuantity() >= 10) {
					addQuantity = 10;
				}
				else {
					addQuantity = cartItem.getQuantity() + quantity;
				}
				
				cartItem.setQuantity(addQuantity); 
			}else { 
				cartItem = new Cart();
				cartItem.setQuantity(quantity); 
				cartItem.setUser(user);
				cartItem.setRecommendmenu(menu); 
			} 
			cartRepo.save(cartItem);
				  
			return addQuantity; 
	  }
	
	public double updateQuantity(Integer menuId, Integer quantity, User user) {
		cartRepo.updateQuantity(quantity, menuId, user.getId());
		RecommendMenu menu = menuRepo.findById(menuId).get();
		double subtotal = menu.getPrice() * quantity;
		
		return subtotal;
	}
	
	public void removeMenu(Integer menuId, User user) {
		cartRepo.deleteByUserAndMenu(user.getId(), menuId);
	}
	
	
}
