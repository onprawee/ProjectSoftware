package com.example.demo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class User {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id ;
	 
	 @Column(nullable = false, unique = true, length = 45)
	 private String username;
	 
	 @Column(nullable = false, length = 64)
	 private String password;
	 
	 @Column(nullable = false, length = 64)
	 private String phone;
	 
	 //One_To_One
	 @OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	 @PrimaryKeyJoinColumn
	 private Cart cart;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	 
	 
	 

	 
	 

}
