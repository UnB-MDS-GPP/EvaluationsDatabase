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

public class TestCourse {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
		Course course = new Course();
		course.setName("one");
		course.save();

		course = new Course();
		course.setName("two");
		course.save();
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
	public void shouldCreateNewCourseOnDataBase() throws ClassNotFoundException, SQLException {
		int initialCount = Course.count();

		Course course = new Course();
		course.setName("A new course");

		assertEquals(true, course.save());
		assertEquals(initialCount, Course.count()-1);
		course.delete();
	}

	@Test
	public void shouldCountCoursesOnDataBase() throws ClassNotFoundException, SQLException {
		int initialCount = Course.count();
		Course course = new Course();
		course.setName("Course");
		course.save();
		assertEquals(initialCount+1, (int)Course.count());
		assertEquals(Course.getAll().size(), (int)Course.count());
		course.delete();
	}

	@Test
	public void shouldGetCourseOnDataBase() throws ClassNotFoundException, SQLException {
		Course course1 = new Course();
		course1.setName("A new course");
		course1.save();

		Course course2 = Course.get(Course.last().getId());

		assertEquals(course1.getName(), course2.getName());
		course1.delete();
	}

	@Test
	public void shouldGetAllCoursesOnDataBase() throws ClassNotFoundException, SQLException {
		int total = Course.count();
		assertEquals(total, Course.getAll().size());
	}
	
	@Test
	public void shouldGetTheFirstCourseOnDataBase() throws ClassNotFoundException, SQLException {
		Course first = Course.first();
		assertEquals(first.getName(), Course.getAll().get(0).getName());
	}

	@Test
	public void shouldGetTheLastCourseOnDataBase() throws ClassNotFoundException, SQLException {
		Course last = Course.last();

		ArrayList<Course> courses = Course.getAll();
		assertEquals(last.getName(), courses.get(courses.size()-1).getName());
	}
	
	@Test
	public void shouldGetCourseInstitutionsOnDataBase()
			throws ClassNotFoundException, SQLException {
		Course course = new Course();
		course.setName("Java");
		course.save();
		Institution institution = new Institution();
		institution.setAcronym("one");
		institution.save();
		Institution institution1 = new Institution();
		institution1.setAcronym("two");
		institution1.save();
		course.addInstitution(institution);
		course.addInstitution(institution1);
		assertEquals("one", Course.last().getInstitutions().get(0).getAcronym());
		assertEquals("two", Course.last().getInstitutions().get(1).getAcronym());
		assertEquals(2, Course.last().getInstitutions().size());
		course.delete();
		institution.delete();
		institution1.delete();
	}
	
	@Test
	public void shouldCreateCourseInstitutionOnDataBase()
			throws ClassNotFoundException, SQLException {
		Course course = new Course();
		course.setName("Java");
		course.save();
		Institution institution = new Institution();
		institution.setAcronym("one");
		institution.save();
		course.addInstitution(institution);
		assertEquals("one", Course.last().getInstitutions().get(0).getAcronym());
		assertEquals(course.getId(), Institution.last().getCourses().get(0).getId());
		course.delete();
		institution.delete();
	}
	
	@Test
	public void shouldGetCoursesWithWhereOnDataBase() throws ClassNotFoundException, SQLException {
		Course course = new Course();
		course.setName("Course AA");
		course.save();

		Course course1 = new Course();
		course1.setName("Course BB");
		course1.save();

		ArrayList<Course> courses1 = Course.getWhere("name", "Course", true);

		ArrayList<Course> courses2 = Course.getWhere("name", "AA", true);

		assertEquals(2, courses1.size());
		assertEquals(1, courses2.size());
		course.delete();
		course1.delete();
	}

	@Test
	public void shouldTellIfAltradyHasACourseWithTheSameName() throws ClassNotFoundException, SQLException {
		assertEquals(true, Course.alreadyExistsWith("name", "one"));
		assertEquals(true, Course.alreadyExistsWith("name", "two"));
		assertEquals(false, Course.alreadyExistsWith("name", "other"));
	}

	@Test
	public void shouldTellIfTheCourseAlreadyHasAnInstitution() throws ClassNotFoundException, SQLException {
		Course course = Course.get(1);

		Institution institutionA = new Institution();
		institutionA.setAcronym("AAA");
		institutionA.save();

		Institution institutionB = new Institution();
		institutionB.setAcronym("BBB");
		institutionB.save();

		course.addInstitution(institutionA);

		assertEquals(true, course.hasInstitution(institutionA));
		assertEquals(false, course.hasInstitution(institutionB));
	}
}
