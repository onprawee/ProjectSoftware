package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	 
	 
	//OneToMany
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private List<Cart> carts;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
	private List<OrderList> order;
	
	//OnetoOne
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn 
	private UserInfo info;


	public UserInfo getInfo() {
		return info;
	}

	public void setInfo(UserInfo info) {
		this.info = info;
	}

	//Getter - Setter
	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
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

	 
	 

	 
	 

}
