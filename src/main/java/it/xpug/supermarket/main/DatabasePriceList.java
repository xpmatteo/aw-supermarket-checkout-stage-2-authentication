package it.xpug.supermarket.main;

import it.xpug.generic.db.*;

public class DatabasePriceList implements PriceList {

	private Database database;

	public DatabasePriceList(Database database) {
		this.database = database;
	}

	@Override
	public int findPrice(String code) {
		String sql = "select price from products where code = ?";
		ListOfRows rows = database.select(sql, code);
		if (0 == rows.size())
			throw new PriceNotFound();
		return (Integer) rows.get(0).get("price");
	}

}
