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
		DataBaseStructures db = new DataBaseStructures("test");
		db.dropDB();
	}


	@Before
	public void setUp() throws Exception {
		crudModel = new GenericCRUDModel();
	}


	@Test
	public void shouldCountAndInsertDataOnDatabase() throws SQLException {
		int initialCount = crudModel.count("artigos");
		this.insertDefaultTestData();

		assertEquals(initialCount+1, crudModel.count("artigos"));
		this.insertDefaultTestData();

		assertEquals(initialCount+2, crudModel.count("artigos"));
	}


	@Test
	public void shouldDeleteDataOnDatabase() throws SQLException {
		this.insertDefaultTestData();
		
		int beforeDeleteCount = crudModel.count("artigos");
		
		crudModel.delete("artigos", 1);
		
		assertEquals(beforeDeleteCount-1, crudModel.count("artigos"));
	}


	@Test
	public void shouldSelectDataOnDatabase() throws SQLException {
		this.insertDefaultTestData();
		ArrayList<String> fields = new ArrayList<String>();
		int count = crudModel.count("artigos");
		
		fields.add("id");
		fields.add("internacionais");
		fields.add("nacionais");
		fields.add("locais");

		ArrayList<Hashtable<String, String>> data = crudModel.select("artigos", fields);
		
		assertEquals(count, data.size());
	}


	private void insertDefaultTestData() throws SQLException {
		Hashtable<String, String> data = new Hashtable<String, String>();
		
		data.put("internacionais", "40");
		data.put("nacionais", "50");
		data.put("locais", "60");
		this.crudModel.insert("artigos", data);
	}
}
