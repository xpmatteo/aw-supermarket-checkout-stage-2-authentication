package it.xpug.supermarket.main;


import it.xpug.generic.db.*;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SupermarketServlet extends HttpServlet {

	private DatabaseConfiguration configuration;

	public SupermarketServlet(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Database database = new Database(configuration);
		SupermarketCheckoutRepository repository = new SupermarketCheckoutRepository(database);
		SupermarketController controller = new SupermarketController(repository, request, response);
		controller.service();
	}
}
