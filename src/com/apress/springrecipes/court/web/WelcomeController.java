package com.apress.springrecipes.court.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

	@RequestMapping(value= "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		Date today = new Date();
		model.addAttribute("today", today);
		return "welcome";
	}
	
	@RequestMapping(value= "/testRedirect", method = RequestMethod.GET)
	public String testRedirect(Model model) {
		return "redirect:welcome";
	}
}