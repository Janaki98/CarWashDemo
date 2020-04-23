package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class Customer {

	@Autowired
	private CustomerService custService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String checkUser(@RequestBody CustomerDetails customer) throws CarWashException {

		try {
			custService.login(customer);
		} catch (Exception e) {
			throw new CarWashException("Please enter correct username and password");
		}
		return "Logged In Successfully";
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String addDetails(@RequestBody CustCarDetails details) throws CarWashException {
	
		try {
			
			custService.addDetails(details);
		}
		catch(Exception e) {
			throw new CarWashException("please try again");
		}
		return "Added Successfully";
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "text/plain")
	public String addUser(@RequestBody CustomerDetails customer) throws CarWashException {

		CustomerDetails newUser;
		try {
			 newUser = custService.register(customer);
		} catch (Exception e) {
			throw new CarWashException("User Already Exists");
		}
		return newUser +" Registered Successfully";
	}

}
