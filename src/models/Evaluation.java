package models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Evaluation extends Bean {

	private int id;
	private int id_institution;
	private int year;
	private String modality;
	private int master_degree_start_year;
	private int doctorate_start_yaear;
	private int triennial_evaluation;
	private int permanent_teachers;
	private int theses;
	private int dissertations;
	private int id_articles;
	private int id_books;
	private int published_works;
	private int artistic_production;

	public Evaluation() {
		// TODO Auto-generated constructor stub
	}

	public Evaluation(int id, int id_institution, int year, String modality,
			int master_degree_start_year, int doctorate_start_yaear,
			int triennial_evaluation, int permanent_teachers, int theses,
			int dissertations, int id_articles, int id_books,
			int published_works, int artistic_production) {
		
		this.id = 0;
		this.id_institution = id_institution;
		this.year = year;
		this.modality = modality;
		this.master_degree_start_year = master_degree_start_year;
		this.doctorate_start_yaear = doctorate_start_yaear;
		this.triennial_evaluation = triennial_evaluation;
		this.permanent_teachers = permanent_teachers;
		this.theses = theses;
		this.dissertations = dissertations;
		this.id_articles = id_articles;
		this.id_books = id_books;
		this.published_works = published_works;
		this.artistic_production = artistic_production;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_institution() {
		return id_institution;
	}

	public void setId_institution(int id_institution) {
		this.id_institution = id_institution;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public int getMaster_degree_start_year() {
		return master_degree_start_year;
	}

	public void setMaster_degree_start_year(int master_degree_start_year) {
		this.master_degree_start_year = master_degree_start_year;
	}

	public int getDoctorate_start_yaear() {
		return doctorate_start_yaear;
	}

	public void setDoctorate_start_yaear(int doctorate_start_yaear) {
		this.doctorate_start_yaear = doctorate_start_yaear;
	}

	public int getTriennial_evaluation() {
		return triennial_evaluation;
	}

	public void setTriennial_evaluation(int triennial_evaluation) {
		this.triennial_evaluation = triennial_evaluation;
	}

	public int getPermanent_teachers() {
		return permanent_teachers;
	}

	public void setPermanent_teachers(int permanent_teachers) {
		this.permanent_teachers = permanent_teachers;
	}

	public int getTheses() {
		return theses;
	}

	public void setTheses(int theses) {
		this.theses = theses;
	}

	public int getDissertations() {
		return dissertations;
	}

	public void setDissertations(int dissertations) {
		this.dissertations = dissertations;
	}

	public int getId_articles() {
		return id_articles;
	}

	public void setId_articles(int id_articles) {
		this.id_articles = id_articles;
	}

	public int getId_books() {
		return id_books;
	}

	public void setId_books(int id_books) {
		this.id_books = id_books;
	}

	public int getPublished_works() {
		return published_works;
	}

	public void setPublished_works(int published_works) {
		this.published_works = published_works;
	}

	public int getArtistic_production() {
		return artistic_production;
	}

	public void setArtistic_production(int artistic_production) {
		this.artistic_production = artistic_production;
	}

	@Override
	public String get(String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(String field, String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> fieldsList() {
		// TODO Auto-generated method stub
		return null;
	}

}
