package com.aaf.fmit.dao.test;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.ItemDAO;
import com.aaf.fmit.dao.MarketDAO;
import com.aaf.fmit.dao.PhotoDAO;
import com.aaf.fmit.dao.UserDAO;
import com.aaf.fmit.dao.UserDO;

public class UserJUnit {

	public UserJUnit() {
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	UserDAO userDAO = new UserDAO();
	CommentDAO commentDAO = new CommentDAO();
	MarketDAO marketDAO = new MarketDAO();
	PhotoDAO photoDAO = new PhotoDAO();
	ItemDAO itemDAO = new ItemDAO();

	@Test // #1
	void testUserDAOCrud() {
		int newUserId;
		String newUserName = "Willy", secondName = "Chris";

		// Get the list of users
		List<UserDO> users = userDAO.findAll(); // db call #1
		int userQty = users.size();
		assert (users != null);
		assert (users.size() > 1);

		// Create new user
		UserDO user = new UserDO();
		user.setName(newUserName);
		newUserId = userDAO.create(user); // db call #2
		assert (newUserId > 0);

		// check find all with new entry
		assert (userDAO.findAll().size() > userQty); // db call #3

		// get the newly created user
		user = userDAO.retrieve(newUserId); // db call #4
		assert (user != null);
		assert (user.getName().equals(newUserName)); // checks that the correct user was retrieved

		// update the newly created user
		user.setName(secondName);
		Integer rowsAffected = userDAO.update(user); // db call #5
		assert (rowsAffected > 0);
		user = userDAO.retrieve(newUserId); // db call #6
		assert (user.getName().equals(secondName));

		// delete new user
		rowsAffected = userDAO.delete(newUserId); // db call #7
		assert (rowsAffected > 0);
		assert (userDAO.findAll().size() == userQty);
	}
}
