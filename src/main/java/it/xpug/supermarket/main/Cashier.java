package it.xpug.supermarket.main;

import java.security.*;

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

	public String encryptedPassword() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
			byte[] digest = md.digest();
			return toHexString(digest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	static String toHexString(byte [] bytes) {
		String symbols = "0123456789abcdef";
		String result = "";
		for (byte b : bytes) {
			int i = b & 0xFF;
			result += symbols.charAt(i / 16);
			result += symbols.charAt(i % 16);
		}
		return result;
	}

}
