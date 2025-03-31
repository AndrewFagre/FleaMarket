package com.aaf.fmit.dao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.aaf.fmit.dao.PhotoDO;
import com.aaf.fmit.dao.UserDAO;

public class PhotoJUnit {
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

	@Test // #6
	void testPhotoDAOCrud() throws IOException {
		int newPhotoId, photoSize;
		int newPhotoSize = 36, secondPhotoSize = 638;
		String newPhoto = "C:/Users/chris/Pictures/R.png", secondPhoto = "C:/Users/chris/Pictures/image001edit.png";

		// Get the list of entities
		List<PhotoDO> photos = photoDAO.findAll(); // db call #1 - findAll()
		int photoQty = photos.size();
		assert (photos != null);
		assert (photos.size() >= 1);

		// Create new entity
		PhotoDO photo = new PhotoDO();
		byte[] photoA = loadPhoto(newPhoto);
		byte[] photoB = loadPhoto(secondPhoto);
		photoSize = photoA.length;
		photo.setPhoto(photoA);
		newPhotoId = photoDAO.create(photo); // db call #2 - create()
		assert (newPhotoId > 0);

		// check find all with new entry
		assert (photoDAO.findAll().size() > photoQty); // db call #3 - findAll()

		// get the newly created entity
		photo = photoDAO.retrieve(newPhotoId); // db call #4 - retrieve()
		assert (photo != null);
		assert (photo.getPhoto().length == photoSize); // checks that the correct entity was retrieved

		// update the newly created entity
		photo.setPhoto(photoB);
		Integer rowsAffected = photoDAO.update(photo); // db call #5 - update()
		assert (rowsAffected > 0);
		photo = photoDAO.retrieve(newPhotoId); // db call #6 - retrieve()
		assert (photo.getPhoto().length != photoSize);

		// delete new entity
		rowsAffected = photoDAO.delete(newPhotoId); // db call #7 - delete()
		assert (rowsAffected > 0);
		assert (photoDAO.findAll().size() == photoQty); // db call #8 - findAll()
	}
	
	  public byte[] loadPhoto(String filePath) throws IOException {
	        File file = new File(filePath); // Locate the photo file
	        byte[] photoBytes = new byte[(int) file.length()]; // Create a byte array of the file size

	        try (FileInputStream fis = new FileInputStream(file)) {
	            fis.read(photoBytes); // Read the file into the byte array
	        }
	        return photoBytes; // Return the byte array
	    }
}
