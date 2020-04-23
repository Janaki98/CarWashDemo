package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Exceptions.CarWashException;
import com.example.demo.entity.CustCarDetails;
import com.example.demo.entity.CustomerDetails;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	Query query = new Query();

	@Override
	public boolean findUser(CustomerDetails user) throws CarWashException {

		String uName = user.getUserName();
		String pwd = user.getPassword();
		try {

			query = query
					.addCriteria(Criteria.where("userName").is(uName).andOperator(Criteria.where("password").is(pwd)));

			boolean dataExists = mongoTemplate.exists(query, "customerDetails");
			if (dataExists) {
				return true;
			}
		} catch (Exception e) {
			throw new CarWashException("reposit error");
		}
		return false;

	}

	@Override
	public boolean addDetails(CustCarDetails details) throws CarWashException {

		try {
			CustCarDetails dataExists = mongoTemplate.insert(details, "custCarDetails");

			if (dataExists != null) {
				return true;
			}
		} catch (Exception e) {
			throw new CarWashException("error occurred");
		}
		return false;
	}

	@Override
	public CustomerDetails addUser(CustomerDetails customer) throws CarWashException {

		String uName = customer.getUserName();
		String pwd = customer.getPassword();
		CustomerDetails addedUser;
		try {

			query = query.addCriteria(Criteria.where("userName").is(uName));
			boolean dataExists = mongoTemplate.exists(query, "customerDetails");

			if (!dataExists) {
				addedUser = mongoTemplate.insert(customer, "customerDetails");
			} else {
				throw new CarWashException("User Already Exists");
			}
		} catch (Exception e) {
			throw new CarWashException("reposit error");
		}
		return addedUser;
	}

}
