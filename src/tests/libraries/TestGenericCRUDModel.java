package tests.libraries;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import libraries.*;

public class TestGenericCRUDModel {
	private GenericCRUDModel crudModel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures("test");
		db.initDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//DataBaseStructures db = new DataBaseStructures("test");
		//db.dropDB();
	}

	@Before
	public void setUp() throws Exception {
		crudModel = new GenericCRUDModel();
	}

	@Test
	public void shouldCountAndInsertDataOnDatabase() throws SQLException {
		Hashtable<String, String> data;
		int initialCount = crudModel.count("artigos");

		data = new Hashtable<String, String>();
		data.put("internacionais", "10");
		data.put("nacionais", "20");
		data.put("locais", "30");

		crudModel.insert("artigos", data);

		assertEquals(initialCount+1, crudModel.count("artigos"));

		data = new Hashtable<String, String>();
		data.put("internacionais", "40");
		data.put("nacionais", "50");
		data.put("locais", "60");

		crudModel.insert("artigos", data);

		assertEquals(initialCount+2, crudModel.count("artigos"));
	}

}
