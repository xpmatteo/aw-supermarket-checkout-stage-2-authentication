package it.xpug.supermarket.main;

import static org.junit.Assert.*;
import it.xpug.generic.db.*;

import org.junit.*;

public class CashierRepositoryTest {
	private Database database = new Database(new PropertyFileDatabaseConfiguration("database.properties"));
	CashierRepository repository = new CashierRepository(database);


	@Before
	public void setUp() throws Exception {
		database.execute("delete from cashiers");
	}

	@Test
	public void initiallyEmpty() throws Exception {
		assertEquals(0, repository.count());
	}

	@Test
	public void createUser() {
		repository.add(new Cashier(1, "x"));
		assertEquals(1, repository.count());
	}

	@Test
	public void authenticationSucceeds() {
		Cashier cashier = new Cashier(1234, "password");
		repository.add(cashier);
		assertTrue(repository.cashierExists(cashier));
	}

	@Test
	public void authenticationFails() throws Exception {
		repository.add(new Cashier(1234, "password"));
		assertFalse("bad id", repository.cashierExists(new Cashier(999, "password")));
		assertFalse("bad password", repository.cashierExists(new Cashier(1234, "wrong")));
	}

}
