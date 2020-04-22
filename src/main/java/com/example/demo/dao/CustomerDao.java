package com.example.demo.dao;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustomerDetails;

public interface CustomerDao {
	
	public boolean findUser(CustomerDetails customer) throws CarWashException;
}
