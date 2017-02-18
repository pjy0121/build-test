package com.aulos.buildtest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aulos.buildtest.service.DummyService;

@Controller
public class DummyController {

	private static final String ATTRIBUTE_INVALID_PARAMS = "attr_invalid_params";
	private static final String ATTRIBUTE_IS_PRIME_NUMBER = "attr_is_prime_number";
	
	@Autowired
	private DummyService dummyService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	private String handleCheckPrimeNumber(HttpServletRequest request, Model model,
										  @RequestParam(value = "number", defaultValue = "0") int value) {
		
		if (value < 2 || value > 100) {
			
			model.addAttribute(ATTRIBUTE_INVALID_PARAMS, true);
			return "error";
		}
		
		boolean isPrimeNumber = dummyService.checkPrimeNumber(value);
		
		if (isPrimeNumber) {
			
			model.addAttribute(ATTRIBUTE_IS_PRIME_NUMBER, true);
		}
		
		return "index";
	}
	
	@ExceptionHandler({ Exception.class })
	private String handleException(Model model) {
		
		model.addAttribute(ATTRIBUTE_INVALID_PARAMS, true);
		
		return "error";
	}
}
