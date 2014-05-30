package it.xpug.generic.db;

import java.util.*;


public class TestDatabaseConfiguration extends DatabaseConfiguration {

	private static Properties properties = new Properties();
	static {
		properties.setProperty("user", "aw_supermarket_checkout");
		properties.setProperty("host", "localhost");
		properties.setProperty("database", "aw_supermarket_checkout_development");
	}

	public TestDatabaseConfiguration() {
		super(properties);
	}

}
