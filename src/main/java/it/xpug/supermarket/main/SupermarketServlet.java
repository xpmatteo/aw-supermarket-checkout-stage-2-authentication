package it.xpug.supermarket.main;


import it.xpug.generic.db.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SupermarketServlet extends HttpServlet {

	private PriceList priceList;
	private Database database;

	public SupermarketServlet() {
		DatabaseConfiguration configuration = new DatabaseConfiguration("database.properties");
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
}
