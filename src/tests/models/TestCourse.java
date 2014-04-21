package tests.models;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import libraries.DataBaseStructures;
import models.Course;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCourse {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
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
	}

	@Test
	public void shouldCountCoursesOnDataBase() throws ClassNotFoundException, SQLException {
		int count1 = Course.count();

		Course course = new Course();
		course.setName("A new course");
		course.save();

		int count2 = Course.count();
		course = new Course();
		course.setName("Other course");
		course.save();

		assertEquals(count1, count2-1);
		assertEquals(count2, Course.count()-1);
	}

	@Test
	public void shouldGetCourseOnDataBase() throws ClassNotFoundException, SQLException {
		Course course1 = new Course();
		course1.setName("A new course");
		course1.save();

		Course course2 = Course.get(Course.last().getId());

		assertEquals(course1.getName(), course2.getName());
	}

	@Test
	public void shouldGetAllCoursesOnDataBase() throws ClassNotFoundException, SQLException {
		Course course;

		course = new Course();
		course.setName("Course A");
		course.save();

		course = new Course();
		course.setName("Course B");
		course.save();

		int total = Course.count();

		assertEquals(total, Course.getAll().size());
	}
	
	@Test
	public void shouldGetTheFirstCourseOnDataBase() throws ClassNotFoundException, SQLException {
		Course course;

		course = new Course();
		course.setName("Course A");
		course.save();

		course = new Course();
		course.setName("Course B");
		course.save();

		Course first = Course.first();

		assertEquals(first.getName(), Course.getAll().get(0).getName());
	}

	@Test
	public void shouldGetTheLastCourseOnDataBase() throws ClassNotFoundException, SQLException {
		Course course;

		course = new Course();
		course.setName("Course A");
		course.save();

		course = new Course();
		course.setName("Course B");
		course.save();

		Course last = Course.last();

		ArrayList<Course> courses = Course.getAll();
		assertEquals(last.getName(), courses.get(courses.size()-1).getName());
	}
	
	@Test
	public void shouldGetCoursesWithWhereOnDataBase() throws ClassNotFoundException, SQLException {
		Course course;

		course = new Course();
		course.setName("Course AA");
		course.save();

		course = new Course();
		course.setName("Course BB");
		course.save();

		ArrayList<Course> courses1 = Course.getWhere("name", "Course", true);

		ArrayList<Course> courses2 = Course.getWhere("name", "AA", true);

		assertEquals(2, courses1.size());
		assertEquals(1, courses2.size());
	}
}
