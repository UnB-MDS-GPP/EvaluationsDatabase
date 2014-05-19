package tests.models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import libraries.DataBaseStructures;
import models.Article;
import models.Articles;
import models.Course;
import models.GenericBeanDAO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import builder.GenerateCRUD;

public class TestArticles {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Article ar = new Article();
		ar.setPublishedConferenceProceedings(2);
		ar.save();
	}

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		GenericBeanDAO gDB = new GenericBeanDAO();
		Articles ars =((Articles)(gDB.selectAllBeans(new Articles()).get(0)));
		//assertEquals("bla", ars.getName());
		assertEquals(2, ars.getPublishedConferenceProceedings());
		ars = (Articles) ars.selectAllBeans().get(0);
		assertEquals(2, ars.getPublishedConferenceProceedings());
		Object o = new GenerateCRUD().getAll();
		System.out.println(o.toString());
	}

}
