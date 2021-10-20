 package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "recommend_id")
	private RecommendMenu recommendmenu;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private int quantity ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RecommendMenu getRecommendmenu() {
		return recommendmenu;
	}

	public void setRecommendmenu(RecommendMenu recommendmenu) {
		this.recommendmenu = recommendmenu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	/*
	 * public Cart(RecommendMenu recommendmenu, int quantity,User user) { this.user
	 * = user; this.recommendmenu = recommendmenu; this.quantity = quantity; }
	 */
	
	
}
