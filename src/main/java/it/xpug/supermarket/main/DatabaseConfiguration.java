package it.xpug.supermarket.main;

import java.sql.*;

public interface DatabaseConfiguration {
	Connection getConnection();
}