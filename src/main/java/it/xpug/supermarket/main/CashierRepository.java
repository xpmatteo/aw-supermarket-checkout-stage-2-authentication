package it.xpug.supermarket.main;

import it.xpug.generic.db.*;

public class CashierRepository {

	private Cashier cashier;
	private Database database;

	public CashierRepository(Database database) {
		this.database = database;
	}

	public void add(Cashier cashier) {
		this.cashier = cashier;
	}

	public boolean cashierExists(int cashierId, String password) {
		return cashier != null && cashier.cashierId() == cashierId && cashier.password().equals(password);
	}

	public long count() {
		String sql = "select count(*) as cashiers_count from cashiers";
		return (Long) database.selectOneValue(sql, "cashiers_count");
	}

}
