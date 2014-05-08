package tests.models;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import libraries.DataBaseStructures;
import models.Course;
import models.Institution;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestInstitution {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
		Institution institution = new Institution();
		institution.setAcronym("one");
		institution.save();
		institution = new Institution();
		institution.setAcronym("two");
		institution.save();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.dropDB();
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldCreateNewInstitutionOnDataBase()
			throws ClassNotFoundException, SQLException {
		Institution institution = new Institution();
		institution.setAcronym("three");
		institution.save();
		assertEquals("three", Institution.last().getAcronym());
		institution = Institution.last();
		institution.delete();
	}

	@Test
	public void shouldCountAllInstitutionsOnDataBase()
			throws ClassNotFoundException, SQLException {
		int initialCount = Institution.count();
		Institution institution = new Institution();
		institution.setAcronym("other");
		institution.save();
		assertEquals(initialCount+1, Institution.count());
		assertEquals(Institution.getAll().size(), Institution.count());
		institution.delete();
	}
	
	@Test
	public void shouldGetAllInstitutionsOnDataBase()
			throws ClassNotFoundException, SQLException {
		assertEquals("one", Institution.getAll().get(0).getAcronym());
		assertEquals("two", Institution.getAll().get(1).getAcronym());
	}

	@Test
	public void shouldGetInstitutionOnDataBase()
			throws ClassNotFoundException, SQLException {
		assertEquals("one", Institution.get(1).getAcronym());
		assertEquals(Institution.getAll().get(0).getAcronym(), Institution.get(1).getAcronym());
	}
	
	@Test
	public void shouldGetFirstInstitutionOnDataBase()
			throws ClassNotFoundException, SQLException {
		assertEquals("one", Institution.first().getAcronym());
		assertEquals(Institution.getAll().get(0).getAcronym(), Institution
				.first().getAcronym());
	}

	@Test
	public void shouldGetLastInstitutionOnDataBase()
			throws ClassNotFoundException, SQLException {
		assertEquals("two", Institution.last().getAcronym());
		assertEquals(Institution.getAll().get(1).getAcronym(), Institution
				.last().getAcronym());
	}
	
	@Test
	public void shouldGetInstitutionCoursesOnDataBase()
			throws ClassNotFoundException, SQLException {
		Institution institution = new Institution();
		institution.setAcronym("three");
		institution.save();
		Course course = new Course();
		course.setName("Java");
		course.save();
		institution.addCourse(course);
		Course course1 = new Course();
		course1.setName("Phyton");
		course1.save();
		institution.addCourse(course1);
		assertEquals("Java", Institution.last().getCourses().get(0).getName());
		assertEquals("Phyton", Institution.last().getCourses().get(1).getName());
		assertEquals(2, Institution.last().getCourses().size());
		institution.delete();
		course.delete();
		course1.delete();
	}
	
	@Test
	public void shouldCreateInstitutionCourseOnDataBase()
			throws ClassNotFoundException, SQLException {
		Institution institution = new Institution();
		institution.setAcronym("three");
		institution.save();
		Course course = new Course();
		course.setName("Java");
		course.save();
		institution.addCourse(course);
		assertEquals("Java", Institution.last().getCourses().get(0).getName());
		assertEquals(course.getId(), Institution.last().getCourses().get(0).getId());
		institution.delete();
		course.delete();
	}

	@Test
	public void shouldGetInstitutionsWithWhereOnDataBase()
			throws ClassNotFoundException, SQLException {
		Institution institution = new Institution();
		institution.setAcronym("Institution AA");
		institution.save();

		Institution institution1 = new Institution();
		institution1.setAcronym("Institution BB");
		institution1.save();

		ArrayList<Institution> institutions1 = Institution.getWhere("acronym", "Institution", true);

		ArrayList<Institution> institutions2 = Institution.getWhere("acronym", "AA", true);
		
		ArrayList<Institution> institutions3 = Institution.getWhere("acronym", "Institution AA", false);
		
		assertEquals(2, institutions1.size());
		assertEquals(1, institutions2.size());
		assertEquals(1, institutions3.size());
		institution.delete();
		institution1.delete();
	}

	@Test
	public void shouldTellIfAltradyHasAnInstitutionWithTheSameAcronym() throws ClassNotFoundException, SQLException {
		assertEquals(true, Institution.alreadyExistsWith("acronym", "one"));
		assertEquals(true, Institution.alreadyExistsWith("acronym", "two"));
		assertEquals(false, Institution.alreadyExistsWith("acronym", "other"));
	}

	@Test
	public void shouldTellIfTheInstitutionAlreadyHasACourse() throws ClassNotFoundException, SQLException {
		Institution institution = Institution.get(1);

		Course courseA = new Course();
		courseA.setName("AAA");
		courseA.save();

		Course courseB = new Course();
		courseB.setName("BBB");
		courseB.save();

		institution.addCourse(courseA);

		assertEquals(true, institution.hasCourse(courseA));
		assertEquals(false, institution.hasCourse(courseB));
	}
}
