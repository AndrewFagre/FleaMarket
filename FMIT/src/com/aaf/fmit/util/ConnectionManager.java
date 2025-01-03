package com.aaf.fmit.util;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionManager {

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
	
	static {
		loadDriver();
		initConnectionPool();
	}
	
	public static Connection get() {
		try {
			return pool.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void closeConnectionPool() {
		try {
			for (Connection sourceConnection : sourceConnections) {
				sourceConnection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void loadDriver() {
		
		try {
			Class.forName(PropertiesUtil.get(DRIVER_KEY));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void initConnectionPool() {
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

	private static Connection open() {
		try {
			return DriverManager.getConnection(PropertiesUtil.get(URL_KEY),
					PropertiesUtil.get(USERNAME_KEY),
					PropertiesUtil.get(PASSWORD_KEY));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
