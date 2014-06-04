package it.xpug.supermarket.main;

import static org.junit.Assert.*;
import it.xpug.generic.db.*;

import org.junit.*;

public class CashierRepositoryTest {
	CashierRepository repository = new CashierRepository(new Database(new DatabaseConfiguration("database.properties")));

	@Test
	public void initiallyEmpty() throws Exception {
		assertEquals(0, repository.count());
	}

//	@Test
//	public void createUser() {
//		repository.add(new Cashier(1, "x"));
//		assertEquals(1, repository.count());
//	}

	@Test
	public void authenticationSucceeds() {
		repository.add(new Cashier(1234, "password"));
		assertTrue(repository.cashierExists(1234, "password"));
	}

	@Test
	public void authenticationFails() throws Exception {
		repository.add(new Cashier(1234, "password"));
		assertFalse("bad id", repository.cashierExists(8888, "password"));
		assertFalse("bad password", repository.cashierExists(1234, "anything"));
	}

}
