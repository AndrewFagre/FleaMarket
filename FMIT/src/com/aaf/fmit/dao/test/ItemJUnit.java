package com.aaf.fmit.dao.test;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aaf.fmit.dao.CommentDAO;
import com.aaf.fmit.dao.CommentDO;
import com.aaf.fmit.dao.ItemDAO;
import com.aaf.fmit.dao.ItemDO;
import com.aaf.fmit.dao.MarketDAO;
import com.aaf.fmit.dao.PhotoDAO;
import com.aaf.fmit.dao.UserDAO;

public class ItemJUnit {
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
	
	@Test // #4
	void testItemDAOCrud() {
		int newItemId;
		String newMarket = "Hot Start Up", secondMarketName = "Sloggy down turn";

		// Get the list of entities
		List<ItemDO> items = itemDAO.findAll(); // db call #1 - findAll()
		int itemQty = items.size();
		assert (items != null);
		assert (items.size() >= 1);

		// Create new entity
		ItemDO item = new ItemDO(), other = new ItemDO();
		String doName;
		String doDescription;
		Integer doBarCode;
		Date doBoughtDate;
		Date doSoldDate;
		Double doBoughtPrice;
		Double doSoldPrice;

		doName = "Skittles";
		doDescription = "long boat";
		doBarCode = 45789360;
		doBoughtDate = java.sql.Date.valueOf("1995-12-01");
		doSoldDate = java.sql.Date.valueOf("2015-12-01");
		doBoughtPrice = 15.8;
		doSoldPrice = 16.8;

		item.setName(doName);
		item.setDescription(doDescription);
		item.setSellerRefId(2);
//			item.setBarCode(doBarCode);
		item.setBoughtDate(doBoughtDate);
		item.setSoldDate(doSoldDate);
		item.setBoughtPrice(doBoughtPrice);
		item.setSoldPrice(doSoldPrice);

		other.setName("Kelci");
		other.setDescription("spoon");
//			other.setBarCode(2100000);
		other.setSellerRefId(2);
		CommentDO comment = new CommentDO();
		comment.setCommentRefId(3);
		other.setComment(comment);
		other.setBoughtDate(java.sql.Date.valueOf("2004-10-15"));
		other.setSoldDate(java.sql.Date.valueOf("2024-10-15"));
		other.setBoughtPrice(20.5);
		other.setSoldPrice(19.5);

		newItemId = itemDAO.create(item); // db call #2 - create()
		assert (newItemId > 0);

		// check find all with new entry
		assert (itemDAO.findAll().size() > itemQty); // db call #3 - findAll()

		// get the newly created entity
		item = itemDAO.retrieve(newItemId); // db call #4 - retrieve()
		assert (item != null);
		assert (item.getItemRefId() > -1);
		assert (item.getName().equals(doName)); // checks that the correct entity was retrieved

		// update the newly created entity
		item.setName("NameREdo");
		// the following update is not affecting any rows... missing refid?
		Integer rowsAffected = itemDAO.update(item); // db call #5 - update()
		assert (rowsAffected > 0);
		item = itemDAO.retrieve(newItemId); // db call #6 - retrieve()
		assert (item.getName().equals("NameREdo"));

		// delete new entity
		rowsAffected = itemDAO.delete(newItemId); // db call #7 - delete()
		assert (rowsAffected > 0);
		assert (itemDAO.findAll().size() == itemQty); // db call #8 - findAll()

		Integer otherId = itemDAO.create(other);
		assert (otherId != null);
		assert (otherId > -1);

	}
}
