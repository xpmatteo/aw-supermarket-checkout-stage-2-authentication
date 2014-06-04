package it.xpug.supermarket.main;

import static org.junit.Assert.*;

import org.junit.*;

public class CashierTest {

	@Test
	public void encryptPassword() {
		Cashier cashier = new Cashier(123, "password");
		String expected = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
		assertEquals(expected, cashier.encryptedPassword());
	}

	@Test
	public void toHexString() throws Exception {
		assertEquals("", Cashier.toHexString(new byte[] {} ));
		assertEquals("04", Cashier.toHexString(new byte[] { 4 } ));
		byte[] bytes = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
		assertEquals("000102030405060708090a0b0c0d0e0f10", Cashier.toHexString(bytes));
		assertEquals("fe", Cashier.toHexString(new byte[] { (byte) 0xFE } ));
	}

}
