package com.aaf.fmit.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chris McDermit
 */
public final class ConnectionManager {

	private static final Logger logger = LogManager.getLogger(ConnectionManager.class);

	private ConnectionManager() {}
		
	private static final String URL_KEY = "db.url"; 
	private static final String USERNAME_KEY = "db.username"; 
	private static final String PASSWORD_KEY = "db.password"; 
	private static final String DRIVER_KEY = "db.driver"; 
	private static final String POOL_SIZE_KEY = "db.pool.size"; 
	private static final Integer DEFAULT_POOL_SIZE = 10;
	private static final String CLOSE = "close";
	private static BlockingQueue<Connection> pool;
	private static List<Connection> sourceConnections;
	
	/**
	 * static initializer 
	 */
	static {
		loadDriver();
		initConnectionPool();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	        shutdown();
	    }));

	}
	
	/**
	 * attains the connection from the pool
	 * 
	 * @return a connection
	 */
	public static Connection get() {
		try {
	        Connection connection = pool.take(); // waits indefinitely (not ideal tbh)
//			if waiting becomes too frequent and long, try the statement below instead	        
//	        Connection connection = pool.poll(timeoutMillis, TimeUnit.MILLISECONDS);
	        logger.trace("Connection borrowed: ", connection);
	        return connection;
	    } catch (InterruptedException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	/**
	 * returns a connection back to the pool
	 * 
	 * @param connection
	 */
	public static void release(Connection connection) {
	    try {
	        pool.put(connection);
	        logger.trace("released a connection", connection);
	    } catch (InterruptedException e) {
	        throw new RuntimeException(e);
	    }
	}

	
	/**
	 * closes the connections
	 */
	public static void shutdown() {
	    for (Connection connection : sourceConnections) {
	        try {
	            if (connection != null && !connection.isClosed()) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	        	logger.error("Error closing connection: " + e.getMessage(), connection);
	        }
	    }
	}

	/**
	 * loads the assigned driver
	 */
	private static void loadDriver() {
		try {
			Class.forName(PropertiesUtil.get(DRIVER_KEY));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * does the initiation
	 */
	private static void initConnectionPool() {
		logger.trace("initiating the connection pool");
		String poolSize = PropertiesUtil.get(POOL_SIZE_KEY);
		Integer size = poolSize == null ? DEFAULT_POOL_SIZE : Integer.parseInt(poolSize); 
		pool = new ArrayBlockingQueue<Connection>(size);
		sourceConnections = new ArrayList<Connection>(size);
		for (int i = 0; i < size; i++) {
			Connection connection = open();
			Connection proxyConnection = (Connection) Proxy.newProxyInstance(
					ConnectionManager.class.getClassLoader(),
					new Class[]{Connection.class},
					(proxy, method, args) -> method.getName().equals(CLOSE) ? pool.add((Connection) proxy) : method.invoke(connection, args));
			pool.add(proxyConnection);
			sourceConnections.add(connection);
		}
	}

	/**
	 * gets a connection
	 * 
	 * @return the newly created connection
	 */
	private static Connection open() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(PropertiesUtil.get(URL_KEY),
					PropertiesUtil.get(USERNAME_KEY),
					PropertiesUtil.get(PASSWORD_KEY));
			logger.trace("opening a new connection", connection);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
}
