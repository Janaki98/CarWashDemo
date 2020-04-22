package com.example.demo.service;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;

public interface CustomerService {

	boolean login(CustomerDetails customer) throws CarWashException;

	boolean addDetails(CustCarDetails details) throws CarWashException;


}
