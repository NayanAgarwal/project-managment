package com.jrp.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jrp.pma.entities.UserAccount;
import com.jrp.pma.services.SecurityService;

@Controller
public class SecurityController {
	
	@Autowired
	SecurityService secSvc;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@GetMapping("/register")
	public String getRegistrationPage(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount",userAccount);
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String registerUser(Model model,UserAccount userAccount)
	{
		userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
		secSvc.save(userAccount);
		return "redirect:/";
	}

}
