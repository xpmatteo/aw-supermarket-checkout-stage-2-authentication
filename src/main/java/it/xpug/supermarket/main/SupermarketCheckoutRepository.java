package it.xpug.supermarket.main;

import it.xpug.generic.db.*;

public class SupermarketCheckoutRepository {

	private Database database;

	public SupermarketCheckoutRepository(Database database) {
		this.database = database;
	}

	public SupermarketCheckout findById(int supermarketCheckoutId) {
		PriceList priceList = new DatabasePriceList(database);
		SupermarketCheckout checkout = new SupermarketCheckout(priceList);
		// do something with the database ...
		checkout.setTotal(1234);
		return checkout;
	}

	public void save(SupermarketCheckout checkout) {
		// do something with the database ...
	}
}
