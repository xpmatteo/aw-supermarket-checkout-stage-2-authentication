package it.xpug.supermarket.main;

import it.xpug.generic.db.*;

public class SupermarketCheckoutRepository {

	private PriceList priceList;
	private Database database;

	public SupermarketCheckoutRepository(PriceList priceList, Database database) {
		this.priceList = priceList;
		this.database = database;
	}

	public SupermarketCheckout findById(int supermarketCheckoutId) {
		SupermarketCheckout checkout = new SupermarketCheckout(priceList);
		// do something with the database ...
		checkout.setTotal(1234);
		return checkout;
	}
}
