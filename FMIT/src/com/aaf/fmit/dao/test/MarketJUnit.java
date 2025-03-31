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
import com.aaf.fmit.dao.MarketDO;
import com.aaf.fmit.dao.PhotoDAO;
import com.aaf.fmit.dao.UserDAO;

public class MarketJUnit {

	public MarketJUnit() {
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

	@Test // #3
	void testMarketDAOCrud() {
		int newMarketId;
		String newMarket = "Hot Start Up", secondMarketName = "Sloggy down turn";

		// Get the list of entities
		List<MarketDO> markets = marketDAO.findAll(); // db call #1 - findAll()
		int marketQty = markets.size();
		assert (markets != null);
		assert (markets.size() >= 1);

		// Create new entity
		MarketDO market = new MarketDO();
		market.setName(newMarket);
		newMarketId = marketDAO.create(market); // db call #2 - create()
		assert (newMarketId > 0);

		// check find all with new entry
		assert (marketDAO.findAll().size() > marketQty); // db call #3 - findAll()

		// get the newly created entity
		market = marketDAO.retrieve(newMarketId); // db call #4 - retrieve()
		assert (market != null);
		assert (market.getName().equals(newMarket)); // checks that the correct entity was retrieved

		// update the newly created entity
		market.setName(secondMarketName);
		Integer rowsAffected = marketDAO.update(market); // db call #5 - update()
		assert (rowsAffected > 0);
		market = marketDAO.retrieve(newMarketId); // db call #6 - retrieve()
		assert (market.getName().equals(secondMarketName));

		// delete new entity
		rowsAffected = marketDAO.delete(newMarketId); // db call #7 - delete()
		assert (rowsAffected > 0);
		assert (marketDAO.findAll().size() == marketQty); // db call #8 - findAll()
	}
}
