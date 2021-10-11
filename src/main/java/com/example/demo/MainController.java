package com.example.demo;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/")
	public String CustomerForm(Model model) {
		return "Home";
	}
	@GetMapping("/Menu")
	  public String showForm(Model model) {
	      return "Menu";
	  }

}
