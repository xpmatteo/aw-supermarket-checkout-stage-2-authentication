package it.xpug.supermarket.main;

import static org.junit.Assert.*;
import it.xpug.generic.db.*;

import org.junit.*;

public class DatabasePriceListTest {
	Database database = new Database(new PropertyFileDatabaseConfiguration("database.properties"));
	PriceList priceList = new DatabasePriceList(database);

	@Test
	public void findsAPrice() {
		assertEquals(111, priceList.findPrice("A"));
	}

	@Test(expected=PriceList.PriceNotFound.class)
	public void reportsPriceNotFound() throws Exception {
		priceList.findPrice("XXX");
	}

}
