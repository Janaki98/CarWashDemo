package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerDetails;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public boolean findUser(CustomerDetails user) throws Exception {

		String uName = user.getUserName();
		try {
			Query query = new Query();
			System.out.println(query);
			query = query.addCriteria(Criteria.where("userName").is(uName));

			System.out.println(query);
			System.out.println("reposit");

			boolean dataExists = mongoTemplate.exists(query, "customerDetails");
			if (dataExists) {
				System.out.println("data exists");
				return true;
			}
		} catch (Exception e) {
			throw new Exception("reposit error");
		}
		return false;

	}

}
