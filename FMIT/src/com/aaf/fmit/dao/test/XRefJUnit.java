package com.aaf.fmit.dao.test;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aaf.fmit.dao.MarketXRefDAO;
import com.aaf.fmit.dao.MarketXRefDO;


/**
 * @author Chris McDermit
 */
class XRefJUnit {
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

	MarketXRefDAO dao = new MarketXRefDAO();
	
	@Test // #5 Existing itemRefIds 1/3/4/6/11
	  void testMarketXRefDAOCrud() {
		  int itemId = 1, market1Id = 1, market2Id = 2, newMarketXRefId;
		  
		  
		  // Get the list of entities
		  List<MarketXRefDO> marketItems = dao.findAll(); // db call #1 - findAll()
		  int marketItemQty = marketItems.size();
		  assert(marketItems != null);
//		  assert(marketItems.size() >= 1);
		  
		  // Create new entity
		  MarketXRefDO marketItem = new MarketXRefDO(); 
		  marketItem.setItemRefId(itemId);
		  marketItem.setMarketRefId(market2Id);
		  newMarketXRefId = dao.create(marketItem); // db call #2 - create()
		  assert(newMarketXRefId > 0);
		  
		  // check find all with new entry
		  assert(dao.findAll().size() > marketItemQty); // db call #3 - findAll()
		  
		  // get the newly created entity
		  marketItem = dao.retrieve(newMarketXRefId); // db call #4 - retrieve()
		  assert(marketItem != null); 
		  assert(marketItem.getMarketXRefId() == newMarketXRefId); // checks that the correct entity was retrieved
		  assert(marketItem.getMarketRefId() == market2Id); // checks that the correct entity was retrieved
		  assert(marketItem.getItemRefId() == itemId); // checks that the correct entity was retrieved
		  
		  // update the newly created entity
		  marketItem.setMarketRefId(market1Id);
		  Integer rowsAffected = dao.update(marketItem); // db call #5 - update()
		  assert(rowsAffected > 0);
		  marketItem = dao.retrieve(newMarketXRefId);  // db call #6 - retrieve()
		  assert(marketItem.getMarketRefId() == market1Id);
		  
		  // delete new entity
		  rowsAffected = dao.delete(newMarketXRefId);  // db call #7 - delete()
		  assert(rowsAffected > 0);
		  assert(dao.findAll().size() == marketItemQty); // db call #8 - findAll()
	  }
}
