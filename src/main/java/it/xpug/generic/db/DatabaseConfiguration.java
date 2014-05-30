package it.xpug.generic.db;

import static java.lang.String.*;

import java.sql.*;
import java.util.*;

public class DatabaseConfiguration {

	private Properties properties;

	public DatabaseConfiguration(Properties properties) {
		this.properties = properties;
	}

	public Connection getConnection() {
		try {
			return tryGetConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Connection tryGetConnection() throws ClassNotFoundException, SQLException {
		String host = properties.getProperty("host");
		String database = properties.getProperty("database");
		String url = format("jdbc:postgresql://%s/%s", host, database);
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(url);
		connection.setAutoCommit(false);
		return connection;
	}

}
