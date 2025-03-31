package com.aaf.fmit.dao.test;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.CommentDO;
import com.aaf.fmit.dao.ItemDAO;
import com.aaf.fmit.dao.MarketDAO;
import com.aaf.fmit.dao.PhotoDAO;
import com.aaf.fmit.dao.UserDAO;

public class CommentJUnit {

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

	@Test // #2
	void testCommentDAOCrud() {
		int newCommentId;
		String newComment = "I'm new!!", secondComment = "wrote over.";

		// Get the list of entities
		List<CommentDO> comments = commentDAO.findAll(); // db call #1 - findAll()
		int commentQty = comments.size();
		assert (comments != null);
		assert (comments.size() > 1);

		// Create new entity
		CommentDO comment = new CommentDO();
		comment.setComment(newComment);
		newCommentId = commentDAO.create(comment); // db
													// call
													// #2 -
													// create()
		assert (newCommentId > 0);

		// check find all with new entry
		assert (commentDAO.findAll().size() > commentQty); // db call #3 - findAll()

		// get the newly created entity
		comment = commentDAO.retrieve(newCommentId); // db call #4 - retrieve()
		assert (comment != null);
		assert (comment.getComment().equals(newComment)); // checks that the correct entity was
															// retrieved

		// update the newly created entity
		comment.setComment(secondComment);
		Integer rowsAffected = commentDAO.update(comment); // db call #5 - update()
		assert (rowsAffected > 0);
		comment = commentDAO.retrieve(newCommentId); // db call #6 - retrieve()
		assert (comment.getComment().equals(secondComment));

		// delete new entity
		rowsAffected = commentDAO.delete(newCommentId); // db call #7 - delete()
		assert (rowsAffected > 0);
		assert (commentDAO.findAll().size() == commentQty); // db call #8 - findAll()
	}
}
