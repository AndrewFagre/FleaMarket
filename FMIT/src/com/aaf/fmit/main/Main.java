/**
 * 
 */
package com.aaf.fmit.main;

import java.util.List;

import com.aaf.fmit.dao.GenericDAO;
import com.aaf.fmit.dao.UserDAO;
import com.aaf.fmit.dao.UserDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
/**
 * https://github.com/AndrewFagre/FleaMarket/
 * -Dlog4j.configurationFile=src/main/resources/log4j2.xml
 */
public class Main {
	

private static final Logger logger = LogManager.getLogger(Main.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
     //   Configurator.initialize("log4j2.properties"); 
		
		String myPropertyValue = System.getProperty("Dlog4j.configurationFile"); 
		System.out.println(myPropertyValue);
		System.out.println(System.getProperty("Dlog4j2.statusLoggerLevel"));
        logger.info("This is an informational message.");
        logger.debug("This is a debug message.");
        logger.error("This is an error message.");
        
		GenericDAO<UserDO, Integer> dataService = new UserDAO();
		List<UserDO> items = dataService.findAll();
		for (UserDO object : items) {
			System.out.println(object);
		}
	}

}
