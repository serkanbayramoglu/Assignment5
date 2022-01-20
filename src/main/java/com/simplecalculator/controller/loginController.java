package com.simplecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplecalculator.entity.Admin;
import com.simplecalculator.repository.AdminRepository;



@Controller
public class loginController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@RequestMapping("/")
	public String showHome() {
		return "loginScreen";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String isValidUser(@RequestParam("username") String username, @RequestParam("password") String password,
			ModelMap map) {
		String page = "loginScreen";

		Admin data = adminRepository.findByUsername(username);


		if (password.equals(data.getPassword())) {
						
			map.addAttribute("firstname", data.getFirstname());
			map.addAttribute("lastname", data.getLastname());
			map.addAttribute("username", username);
			map.addAttribute("password", password);
			page = "calculatorScreen";
		} else {
			map.addAttribute("errorMessage", "Invalid login credentials");
		}
		return page;
	}
}
