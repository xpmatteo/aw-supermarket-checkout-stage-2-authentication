package it.xpug.supermarket.main;


import it.xpug.generic.db.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SupermarketServlet extends HttpServlet {

	private SupermarketCheckout checkout;

	public SupermarketServlet() {
		Properties properties = getDatabaseProperties();
		DatabaseConfiguration configuration = new DatabaseConfiguration(properties);
		Database database = new Database(configuration);
		PriceList priceList = new DatabasePriceList(database);
		checkout = new SupermarketCheckout(priceList);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupermarketController controller = new SupermarketController(checkout, request, response);
		controller.service();
	}

	private Properties getDatabaseProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("database.properties"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return properties;
	}
}
