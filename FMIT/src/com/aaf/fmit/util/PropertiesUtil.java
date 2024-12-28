<<<<<<< HEAD
package com.aaf.fmit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

	private PropertiesUtil() {	}
	private static final Properties PROPERTIES = new Properties();
	
	static {
		loadProperties();
	}

	private static void loadProperties() {
		try (InputStream stream = new FileInputStream("resources/application.properties")) {
			PROPERTIES.load(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}
}
=======
package com.aaf.fmit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

	private PropertiesUtil() {	}
	private static final Properties PROPERTIES = new Properties();
	
	static {
		loadProperties();
	}

	private static void loadProperties() {
		try (InputStream stream = new FileInputStream("resources/application.properties")) {
			PROPERTIES.load(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String key) {
		return PROPERTIES.getProperty(key);
	}
}
>>>>>>> refs/remotes/origin/main
