package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CartTest {

	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testAddOneCartItem() {
		RecommendMenu recommendMenu = entityManager.find(RecommendMenu.class, 5);
		User user = entityManager.find(User.class, 17);
		
		Cart newItem = new Cart();
		newItem.setUser(user);
		newItem.setRecommendmenu(recommendMenu);
		newItem.setQuantity(2);
		
		Cart savecart = cartRepo.save(newItem);
		
		assertTrue(savecart.getId()>0);
	}
	
	@Test
	public void testGetCartItemsByUser() {
	User user = new User();
	user.setId(17);
		
	List<Cart> cart = cartRepo.findByUser(user);
		
	assertEquals(2,cart.size());
	}
	
}
