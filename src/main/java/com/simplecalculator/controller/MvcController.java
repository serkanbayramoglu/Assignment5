package com.simplecalculator.controller;


import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplecalculator.entity.Admin;
import com.simplecalculator.repository.AdminRepository;




@Controller
@Transactional
public class MvcController {
	
	@Autowired
	AdminRepository adminRepository;
	

	public String validate(String username, String password, String page, String newPage) {
		Admin data = adminRepository.findByUsername(username);
		
		if (!(password.equals(data.getPassword()))) {
			page = newPage;
		}
		return page;
	}

	public boolean validate(String username, String password) {
		Admin data = adminRepository.findByUsername(username);

		boolean page = false;
		if (password.equals(data.getPassword())) {
			page = true;
		}
		return page;
	}
	
	@RequestMapping(value = "calculatorScreen", method = RequestMethod.POST)
	public String showMain(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, ModelMap map) {
		
		String page = validate(username, password, "mainScreen", "loginScreen");
		
		map.addAttribute("firstname", firstname);
		map.addAttribute("lastname", lastname);
		map.addAttribute("username", username);
		map.addAttribute("password", password);
		
		return page;

		}
	

}
