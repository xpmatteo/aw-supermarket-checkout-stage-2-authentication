package it.xpug.generic.db;

import static org.junit.Assert.*;

import java.sql.*;

import org.junit.*;


public class DatabaseTest {


	private PropertyFileDatabaseConfiguration configuration;
	private Database database;

	@Before
	public void setUp() throws Exception {
		configuration = new PropertyFileDatabaseConfiguration("database.properties");
		database = new Database(configuration);
		database.execute("drop table if exists prova");
		database.execute("create table prova( id serial, name varchar(255) );");
	}


	@Test
	public void configurationReturnsAConnection() throws Exception {
		Connection connection = configuration.getConnection();
		assertNotNull(connection);
	}

	@Test
	public void testSelectSum() throws Exception {
		String sql = "select 3 + 4 as sum";
		assertEquals(7, database.selectOneValue(sql, "sum"));
	}

	@Test
	public void testSelectOneFromTable() throws Exception {
		database.execute("insert into prova (name) values (?);", "pippo");
		assertEquals("pippo", database.selectOneValue("select * from prova", "name"));
	}

	@Test
	public void testSelectAllFromTable() throws Exception {
		database.execute("insert into prova (name) values (?);", "pluto");
		ListOfRows rows = database.select("select * from prova where name like ?", "pl%");
		assertEquals(1, rows.size());
	}

}
