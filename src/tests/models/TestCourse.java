package tests.models;

import static org.junit.Assert.*;
import libraries.DataBaseStructures;
import models.Course;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCourse {

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
	public void shouldCreateNewCursoOnDataBase() {
		int initialCount = Course.count();

		Course curso = new Course();
		curso.setName("Um Novo curso");

		assertEquals(true, curso.save());
		assertEquals(initialCount, Course.count()-1);

		assertEquals(curso.getId(), Course.last().getId());
	}

	@Test
	public void shouldDeleteCursoOnDataBase() {
		Course curso = new Course();
		curso.setName("Um Novo curso");
		curso.save();

		curso = new Course();
		curso.setName("Outro nome");
		curso.save();

		int count = Course.count();

		assertEquals(true, curso.delete());
		assertEquals(count-1, Course.count());
	}
}
