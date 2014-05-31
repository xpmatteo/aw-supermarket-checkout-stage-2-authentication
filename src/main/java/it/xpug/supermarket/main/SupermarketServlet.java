package it.xpug.supermarket.main;


import it.xpug.generic.db.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SupermarketServlet extends HttpServlet {

	private Database database;

	public SupermarketServlet() {
		DatabaseConfiguration configuration = new DatabaseConfiguration("database.properties");
		database = new Database(configuration);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupermarketCheckoutRepository repository = new SupermarketCheckoutRepository(database);
		SupermarketCheckout checkout = repository.findById(0);
		SupermarketController controller = new SupermarketController(checkout , request, response);
		controller.service();
	}
}
