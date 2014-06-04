package it.xpug.supermarket.main;

import it.xpug.generic.db.*;

public class CashierRepository {

	private Database database;

	public CashierRepository(Database database) {
		this.database = database;
	}

	public void add(Cashier cashier) {
		String sql = "insert into cashiers (id, encrypted_password) values (?, ?)";
		database.execute(sql, cashier.cashierId(), cashier.encryptedPassword());
	}

	public boolean cashierExists(Cashier cashier) {
		String sql = "select * from cashiers where id = ? and encrypted_password = ?";
		ListOfRows rows = database.select(sql, cashier.cashierId(), cashier.encryptedPassword());
		return rows.size() != 0;
	}

	public long count() {
		String sql = "select count(*) as cashiers_count from cashiers";
		return (Long) database.selectOneValue(sql, "cashiers_count");
	}

}
