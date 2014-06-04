package it.xpug.supermarket.main;

import static org.junit.Assert.*;
import it.xpug.generic.db.*;

import org.junit.*;

public class SupermarketCheckoutRepositoryTest {

	private Database database = new Database(new DatabaseConfiguration("database.properties"));
	SupermarketCheckoutRepository repository = new SupermarketCheckoutRepository(database);

	@Test
	public void findsCheckouts() throws Exception {
		assertEquals("id 0", 0, repository.findById(0).total());
		assertEquals("id 1", 100, repository.findById(1).total());
	}

	@Test(expected=SupermarketCheckoutRepository.CheckoutNotFound.class)
	public void checkoutNotFound() throws Exception {
		repository.findById(99);
	}

	@Test
	public void save() throws Exception {
		SupermarketCheckout checkout = repository.findById(0);
		checkout.scan("A");
		assertEquals(111, checkout.total());

		repository.save(checkout);

		SupermarketCheckout reloaded = repository.findById(0);
		assertEquals(111, reloaded.total());
	}

}
