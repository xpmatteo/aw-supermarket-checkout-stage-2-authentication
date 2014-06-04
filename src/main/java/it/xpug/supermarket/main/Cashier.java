package it.xpug.supermarket.main;

public class Cashier {

	private int cashierId;
	private String password;

	public Cashier(int cashierId, String password) {
		this.cashierId = cashierId;
		this.password = password;
	}

	public int cashierId() {
		return cashierId;
	}

	public String password() {
		return password;
	}

}
