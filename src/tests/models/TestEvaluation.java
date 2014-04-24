package tests.models;

import static org.junit.Assert.*;

import java.sql.SQLException;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import libraries.DataBaseStructures;
import models.Article;
import models.Book;
import models.Course;
import models.Evaluation;
import models.Institution;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

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
		course.save();
		
		Article article = new Article();
		article.setInternationals(Integer.parseInt("1"));
		article.save();
		
		Book book = new Book();
		book.setIntegralText(Integer.parseInt("1"));
		book.save();
		
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
		
		institution = new Institution();
		institution.setAcronym("2");
		institution.save();
		
		course = new Course();
		course.setName("name course 2");
		course.save();
		
		article = new Article();
		article.setInternationals(Integer.parseInt("2"));
		article.save();
		
		book = new Book();
		book.setIntegralText(Integer.parseInt("2"));
		book.save();
		
		evaluation = new Evaluation();
		evaluation.setIdInstitution(institution.getId());
		evaluation.setIdCourse(course.getId());
		evaluation.setYear(Integer.parseInt("2015"));
		evaluation.setModality("modality 2");
		evaluation.setMasterDegreeStartYear(Integer.parseInt("2001"));
		evaluation.setDoctorateStartYear(Integer.parseInt("2011"));
		evaluation.setTriennialEvaluation(Integer.parseInt("2004"));
		evaluation.setPermanentTeachers(Integer.parseInt("2"));
		evaluation.setTheses(Integer.parseInt("3"));
		evaluation.setDissertations(Integer.parseInt("4"));
		evaluation.setIdArticles(article.getId());
		evaluation.setIdBooks(book.getId());
		evaluation.setPublishedWorks(Integer.parseInt("5"));
		evaluation.setArtisticProduction(Integer.parseInt("6"));
		evaluation.save();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.dropDB();
	}

	@Test
	public void test() throws Exception {
	}
	
	@Test
	public void shouldCreateNewEvaluationOnDataBase() throws ClassNotFoundException, SQLException{
		int initialCount = Evaluation.count();
		
		Institution institution = new Institution();
		institution.setAcronym("1");
		institution.save();
		
		Course course = new Course();
		course.setName("name course");
		course.save();
		
		Article article = new Article();
		article.setInternationals(Integer.parseInt("1"));
		article.save();
		
		Book book = new Book();
		book.setIntegralText(Integer.parseInt("1"));
		book.save();
		
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
		
		assertEquals(true, evaluation.save());
		assertEquals(initialCount, Evaluation.count()-1);
		assertEquals("1", Institution.get(Evaluation.last().getIdInstitution()).getAcronym());
		assertEquals("name course", Course.get(Evaluation.last().getIdCourse()).getName());
		assertEquals("1", Article.get(Evaluation.last().getIdArticles()).getInternationals());
		assertEquals("1", Book.get(Evaluation.last().getIdBooks()).getIntegralText());
		
		institution.delete();
		course.delete();
		book.delete();
		article.delete();
		evaluation.delete();
	}
	
	@Test
	public void shouldCountEvaluationsOnDataBase() throws ClassNotFoundException, SQLException{
		int initialCount = Evaluation.count();
	
		Evaluation evaluation = new Evaluation();
		evaluation.setYear(2000);
		evaluation.setModality("modality");
		evaluation.setMasterDegreeStartYear(1);
		evaluation.save();
		
		assertEquals(initialCount+1, (int)Evaluation.count());
		assertEquals(Evaluation.getAll().size(), (int)Evaluation.count());
		
		evaluation.delete();
	}
	
	@Test
	public void shouldGetEvaluationOnDataBase() throws ClassNotFoundException, SQLException{
		
		Evaluation evaluation_1 = new Evaluation();
		evaluation_1.setDoctorateStartYear(1);
		evaluation_1.setTriennialEvaluation(2);
		evaluation_1.setPermanentTeachers(3);
		evaluation_1.save();
		
		Evaluation evaluation_2 = Evaluation.get(Evaluation.last().getId());
		assertEquals(evaluation_1.getDoctorateStartYear(), evaluation_2.getDoctorateStartYear());
		assertEquals(evaluation_1.getTriennialEvaluation(), evaluation_2.getTriennialEvaluation());
		assertEquals(evaluation_1.getPermanentTeachers(), evaluation_2.getPermanentTeachers());
		
		evaluation_1.delete();
	}
	
	@Test
	public void shouldGetAllEvaluationsOnDataBase() throws ClassNotFoundException, SQLException {
		int total = Evaluation.count();
		assertEquals(total, Evaluation.getAll().size());
		assertEquals(Evaluation.first().getTheses(), Evaluation.getAll().get(0).getTheses());
		assertEquals(Evaluation.first().getDissertations(), Evaluation.getAll().get(0).getDissertations());
	}
	
	@Test
	public void shouldGetTheFirstEvaluationOnDataBase() throws ClassNotFoundException, SQLException {
		Evaluation firstEvaluation = Evaluation.first();
		assertEquals(firstEvaluation.getModality(), Evaluation.getAll().get(0).getModality());
	}
	
}
