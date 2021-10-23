package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@Autowired
	RecommendmenuRepository recommendmenurepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInfoRepository infoRepo;
	
	@Autowired
	private CustomerUserDetailsService userServices;
	
	@RequestMapping("/Login")
	public String shoeLogin(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "Login";
		}
		return "redirect:/";
	}
	
	@GetMapping("/Register")
	  public String  showregis(Model model) {
		//-------------
		model.addAttribute("user", new User());
		model.addAttribute("userinfo", new UserInfo());
		
		return "Register";
	}
	@PostMapping("/Sucessfully")
	public String showregis_sucess(User user,UserInfo info) {
		//-------------
		info.setUser(user);
		infoRepo.save(info);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);       
	
		return "register_sucess";
	}
	
	@GetMapping("/")
	  public String showHome(Model model) {
		
		return "Home";
	}
	
	@GetMapping("/Menu")
	  public String showrecommend(Model model) {
		List<RecommendMenu> recommend = (List<RecommendMenu>) recommendmenurepository.findAll();
		System.out.println(recommend.toString());
		model.addAttribute("recommend",recommend);
	      return "Menu";
	  }

	//---------------------------------------------------
	@GetMapping("/About")
	  public String  showabout(Model model) {
		//-

		return "About";
	}
	
	@GetMapping("/Account")
	  public String  showAccount(Model model  ,@AuthenticationPrincipal Authentication authentication) {
		//-
		authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userServices.getCurrentlyLoggedInCustomer(authentication);

		return "Account";
	}

	//------------------------------------------------------------

	


}
