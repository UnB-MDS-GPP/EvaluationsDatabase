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
	
	@Test
	public void shouldUpdatetDataOnDatabase() throws SQLException {
		this.insertDefaultTestData();
		ArrayList<String> fields = new ArrayList<String>();
		int id = 1;
		
		fields.add("id");
		fields.add("internacionais");
		fields.add("nacionais");
		fields.add("locais");

		ArrayList<Hashtable<String, String>> data = crudModel.select("artigos", fields, id);
		
		ArrayList<String> dataBefore = new ArrayList<String>();
		ArrayList<String> dataAfter = new ArrayList<String>();
		
		dataBefore.add(data.get(0).get("id"));
		dataBefore.add(data.get(0).get("internacionais"));
		dataBefore.add(data.get(0).get("nacionais"));
		dataBefore.add(data.get(0).get("locais"));
		
		Hashtable<String, String> newData = new Hashtable<String, String>();
		newData.put("internacionais", "100");
		newData.put("nacionais", "100");
		newData.put("locais", "100");
		
		crudModel.update("artigos", newData, id);
		
		data = crudModel.select("artigos", fields, id);
		dataAfter.add(data.get(0).get("id"));
		dataAfter.add(data.get(0).get("internacionais"));
		dataAfter.add(data.get(0).get("nacionais"));
		dataAfter.add(data.get(0).get("locais"));
		
		assertEquals(dataBefore.get(0), dataAfter.get(0));
		assertNotEquals(dataBefore.get(1), dataAfter.get(1));
		assertNotEquals(dataBefore.get(2), dataAfter.get(2));
		assertNotEquals(dataBefore.get(3), dataAfter.get(3));
	}


	@Test
	public void shouldReturnFirstOrLastRowInATable() throws SQLException {
		this.insertDefaultTestData("1", "2", "3");
		this.insertDefaultTestData("4", "5", "5");
		ArrayList<String> fields = new ArrayList<String>();

		fields.add("id");
		fields.add("internacionais");
		fields.add("nacionais");
		fields.add("locais");

		ArrayList<Hashtable<String, String>> data = crudModel.select("artigos", fields);
		Hashtable<String, String> first = crudModel.firstOrLast("artigos", fields, false);
		Hashtable<String, String> last = crudModel.firstOrLast("artigos", fields, true);

		assertEquals(data.get(0), first);
		assertEquals(data.get(data.size()-1), last);
	}


	private void insertDefaultTestData() throws SQLException {
		Hashtable<String, String> data = new Hashtable<String, String>();
		
		data.put("internacionais", "40");
		data.put("nacionais", "50");
		data.put("locais", "60");
		this.crudModel.insert("artigos", data);
	}

	private void insertDefaultTestData(String internacionais, String nacionais, String locais) throws SQLException {
		Hashtable<String, String> data = new Hashtable<String, String>();

		data.put("internacionais", internacionais);
		data.put("nacionais", nacionais);
		data.put("locais", locais);
		this.crudModel.insert("artigos", data);
	}
}
