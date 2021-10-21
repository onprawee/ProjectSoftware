package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomerUserDetails(user);
	}
	
	public User getCurrentlyLoggedInCustomer(Authentication authentication) {
		if(authentication == null) {
			return null;
		}
		
		User user = null;
		Object principal = authentication.getPrincipal();
		
		if(principal instanceof CustomerUserDetails) {
			user = ((CustomerUserDetails)principal).getUser();
		}else if (principal instanceof User) {
			String username = ((User) principal).getUsername();
			user = userRepository.findByUsername(username);
		}
	
		return user;
	}

}
 