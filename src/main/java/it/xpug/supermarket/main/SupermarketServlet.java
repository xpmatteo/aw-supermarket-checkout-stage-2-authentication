package it.xpug.supermarket.main;


import it.xpug.generic.db.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SupermarketServlet extends HttpServlet {

	private PriceList priceList;
	private Database database;

	public SupermarketServlet() {
		Properties properties = getDatabaseProperties();
		DatabaseConfiguration configuration = new DatabaseConfiguration(properties);
		database = new Database(configuration);
		priceList = new DatabasePriceList(database);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupermarketCheckoutRepository repository = new SupermarketCheckoutRepository(priceList, database);
		SupermarketCheckout checkout = repository.findById(0);
		SupermarketController controller = new SupermarketController(checkout , request, response);
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
