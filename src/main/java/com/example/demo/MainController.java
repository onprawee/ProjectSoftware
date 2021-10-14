package com.example.demo;
 
import java.util.*;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.String;
@Controller // This means that this class is a Controller
public class Maincontroller {
		@RequestMapping("/")
		public String CustomerForm(Model model) {
			return "Login";
		}
		@GetMapping("/Register")
		  public String RegisterForm(Model model) {
		      return "Register";
		  }
		@GetMapping("/Home")
		  public String HomeForm(Model model) {
		      return "Home";
		  }
		@GetMapping("/Menu")
		  public String showForm(Model model) {
		      return "Menu";
		  }
		@GetMapping("/Cart")
		  public String showForm1(Model model) {
		      return "cart";
		  }
		@GetMapping("/About")
		  public String showForm2(Model model) {
		      return "about";
		  }
		@GetMapping("/Account")
		  public String showForm3(Model model) {
		      return "account";
		  }
  }
  
