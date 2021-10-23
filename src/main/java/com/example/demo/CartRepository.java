package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	public List<Cart> findByUser(User user);
	
	public Cart findByUserAndRecommendmenu(User user, RecommendMenu menu);
	
	@Query("UPDATE Cart c SET c.quantity = ?1 WHERE c.recommendmenu.id =?2 " +
			"AND c.user.id = ?3")
	@Modifying
	public void updateQuantity(Integer quantity, Integer menuId,  Integer UserId);
	
	
	@Query("DELETE FROM Cart c WHERE c.user.id =?1 AND c.recommendmenu.id =?2 ")
	@Modifying
	public void deleteByUserAndMenu(Integer userId,Integer menuId);
	
	@Query("DELETE FROM Cart c WHERE c.user.id =?1")
	@Modifying
	public void deleteByUser(Integer userId);
}
