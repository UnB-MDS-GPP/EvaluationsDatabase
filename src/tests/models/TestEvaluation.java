package tests.models;

import static org.junit.Assert.*;
import libraries.DataBaseStructures;
import models.Evaluation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEvaluation {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataBaseStructures db = new DataBaseStructures();
		db.initDB();
		Evaluation evaluation = new Evaluation();
		evaluation.setYear(Integer.parseInt("2014"));
		evaluation.setModality("modality");
		evaluation.setMasterDegreeStartYear(Integer.parseInt("2000"));
		evaluation.setDoctorateStartYear(Integer.parseInt("2010"));
		evaluation.setTriennialEvaluation(Integer.parseInt("2003"));
		evaluation.setPermanentTeachers(Integer.parseInt("1"));
		evaluation.setTheses(Integer.parseInt("2"));
		evaluation.setDissertations(Integer.parseInt("3"));
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
