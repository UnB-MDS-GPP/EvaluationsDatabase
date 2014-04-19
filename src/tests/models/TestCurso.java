package tests.models;

import static org.junit.Assert.*;
import libraries.DataBaseStructures;
import models.Curso;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCurso {

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
		int initialCount = Curso.count();

		Curso curso = new Curso();
		curso.setNome("Um Novo curso");

		assertEquals(true, curso.save());
		assertEquals(initialCount, Curso.count()-1);

		assertEquals(curso.getId(), Curso.last().getId());
	}

	@Test
	public void shouldDeleteCursoOnDataBase() {
		Curso curso = new Curso();
		curso.setNome("Um Novo curso");
		curso.save();

		curso = new Curso();
		curso.setNome("Outro nome");
		curso.save();

		int count = Curso.count();

		assertEquals(true, curso.delete());
		assertEquals(count-1, Curso.count());
	}
}
