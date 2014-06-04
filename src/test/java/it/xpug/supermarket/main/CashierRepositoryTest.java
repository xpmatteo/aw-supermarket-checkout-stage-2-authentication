package it.xpug.supermarket.main;

import static org.junit.Assert.*;
import it.xpug.generic.db.*;

import org.junit.*;

public class CashierRepositoryTest {
	private Database database = new Database(new PropertyFileDatabaseConfiguration("database.properties"));
	CashierRepository repository = new CashierRepository(database);


	@Before
	public void setUp() throws Exception {
		database.execute("delete from cashiers where id >= 1000000");
	}

	@Test
	public void findsBasicUser() throws Exception {
		assertTrue("cassiere 123", repository.cashierExists(new Cashier(123, "secret")));
	}

	@Test
	public void createUser() {
		long before = repository.count();
		repository.add(new Cashier(1000*1000, "x"));
		assertEquals(before + 1, repository.count());
	}

	@Test
	public void authenticationSucceeds() {
		Cashier cashier = new Cashier(1000*1000, "password");
		repository.add(cashier);
		assertTrue(repository.cashierExists(cashier));
	}

	@Test
	public void authenticationFails() throws Exception {
		repository.add(new Cashier(1000*1000, "password"));
		assertFalse("bad id", repository.cashierExists(new Cashier(999, "password")));
		assertFalse("bad password", repository.cashierExists(new Cashier(1000*1000, "wrong")));
	}

}
