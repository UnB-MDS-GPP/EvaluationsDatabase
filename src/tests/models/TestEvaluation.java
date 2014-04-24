package tests.models;

import static org.junit.Assert.*;
import libraries.DataBaseStructures;
import models.Article;
import models.Book;
import models.Course;
import models.Evaluation;
import models.Institution;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEvaluation {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
		Institution institution = new Institution();
		institution.setAcronym("1");
		institution.save();
		
		Course course = new Course();
		course.setName("name course");
		
		Article article = new Article();
		article.setInternationals(Integer.parseInt("1"));
		
		Book book = new Book();
		book.setIntegralText(Integer.parseInt("1"));
		
		Evaluation evaluation = new Evaluation();
		evaluation.setIdInstitution(institution.getId());
		evaluation.setIdCourse(course.getId());
		evaluation.setYear(Integer.parseInt("2014"));
		evaluation.setModality("modality");
		evaluation.setMasterDegreeStartYear(Integer.parseInt("2000"));
		evaluation.setDoctorateStartYear(Integer.parseInt("2010"));
		evaluation.setTriennialEvaluation(Integer.parseInt("2003"));
		evaluation.setPermanentTeachers(Integer.parseInt("1"));
		evaluation.setTheses(Integer.parseInt("2"));
		evaluation.setDissertations(Integer.parseInt("3"));
		evaluation.setIdArticles(article.getId());
		evaluation.setIdBooks(book.getId());
		evaluation.setPublishedWorks(Integer.parseInt("4"));
		evaluation.setArtisticProduction(Integer.parseInt("5"));
		evaluation.save();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
