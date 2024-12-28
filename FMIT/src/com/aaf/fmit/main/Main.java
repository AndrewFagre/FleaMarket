/**
 * 
 */
package com.aaf.fmit.main;

import java.util.List;

import com.aaf.fmit.dao.GenericDAO;
import com.aaf.fmit.dao.UserDAO;
import com.aaf.fmit.dao.UserDO;

/**
 * https://github.com/AndrewFagre/FleaMarket/
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GenericDAO<UserDO, Integer> dataService = new UserDAO();
		List<UserDO> items = dataService.findAll();
		for (UserDO object : items) {
			System.out.println(object);
		}
	}

}
