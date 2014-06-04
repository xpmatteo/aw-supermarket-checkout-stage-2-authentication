package it.xpug.supermarket.main;

import it.xpug.generic.db.*;

public class SupermarketCheckoutRepository {

	private Database database;

	public SupermarketCheckoutRepository(Database database) {
		this.database = database;
	}

	public SupermarketCheckout findById(int supermarketCheckoutId) {
		PriceList priceList = new DatabasePriceList(database);
		SupermarketCheckout checkout = new SupermarketCheckout(supermarketCheckoutId, priceList);
		String sql = "select * from checkouts where id = ?";
		ListOfRows rows = database.select(sql, supermarketCheckoutId);
		if (0 == rows.size()) throw new CheckoutNotFound();
		checkout.setTotal((Integer) rows.get(0).get("total"));
		return checkout;
	}

	public void save(SupermarketCheckout checkout) {
		String sql = "update checkouts set total = ? where id = ?";
		database.execute(sql, checkout.total(), checkout.id());
	}
}
