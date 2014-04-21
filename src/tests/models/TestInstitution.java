package tests.models;

import static org.junit.Assert.*;
import libraries.DataBaseStructures;
import models.Institution;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestInstitution {
	
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
	}

	@Test
	public void shouldCreateNewInstitutionOnDataBase() {
		Institution institution = new Institution();
		institution.setAcronym("Olá");
		institution.save();
		assertEquals("Olá", institution.get(1).getAcronym());
	}

}
