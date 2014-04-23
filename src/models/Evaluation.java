package models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Evaluation extends Bean {

	private Integer id;
	private Integer idInstitution;
	private Integer idCourse;
	private Integer year;
	private String modality;
	private Integer masterDegreeStartYear;
	private Integer doctorateStartYear;
	private Integer triennialEvaluation;
	private Integer permanentTeachers;
	private Integer theses;
	private Integer dissertations;
	private Integer idArticles;
	private Integer idBooks;
	private Integer publishedWorks;
	private Integer artisticProduction;

	public Evaluation() {
		this.id = 0;
		this.identifier = "evaluation";
		this.relationship = "";

	}

	public Evaluation(Integer id) {
		this.id = id;
		this.identifier = "evaluation";
		this.relationship = "";
	}

	@Override
	public String get(String field) {
		if (field.equals("id")) {
			return Integer.toString(this.getId());
		} else if (field.equals("id_institution")) {
			return Integer.toString(this.getIdInstitution());
		} else if (field.equals("id_course")) {
			return Integer.toString(this.getIdCourse());
		} else if (field.equals("year")) {
			return Integer.toString(this.getYear());
		} else if (field.equals("modality")) {
			return getModality();
		} else if (field.equals("master_degree_start_year")) {
			return Integer.toString(this.getMasterDegreeStartYear());
		} else if (field.equals("doctorate_start_year")) {
			return Integer.toString(this.getDoctorateStartYear());
		} else if (field.equals("triennial_evaluation")) {
			return Integer.toString(this.getTriennialEvaluation());
		} else if (field.equals("permanent_teachers")) {
			return Integer.toString(this.getPermanentTeachers());
		} else if (field.equals("theses")) {
			return Integer.toString(this.getTheses());
		} else if (field.equals("dissertations")) {
			return Integer.toString(this.getDissertations());
		} else if (field.equals("id_articles")) {
			return Integer.toString(this.getIdArticles());
		} else if (field.equals("id_books")) {
			return Integer.toString(this.getIdBooks());
		} else if (field.equals("published_works")) {
			return Integer.toString(this.getPublishedWorks());
		} else if (field.equals("artistic_production")) {
			return Integer.toString(this.getArtisticProduction());
		} else
			return "";

	}

	@Override
	public void set(String field, String data){
		if (field.equals("id")) {
			this.setId(Integer.parseInt(data));
		} else if (field.equals("id_institution")) {
			this.setIdInstitution(Integer.parseInt(data));
		} else if (field.equals("id_course")) {
			this.setIdCourse(Integer.parseInt(data));
		} else if (field.equals("year")) {
			this.setYear(Integer.parseInt(data));
		} else if (field.equals("modality")) {
			this.setModality(data);
		} else if (field.equals("master_degree_start_year")) {
			this.setMasterDegreeStartYear(Integer.parseInt(data));
		} else if (field.equals("doctorate_start_year")) {
			this.setDoctorateStartYear(Integer.parseInt(data));
		} else if (field.equals("triennial_evaluation")) {
			this.setTriennialEvaluation(Integer.parseInt(data));
		} else if (field.equals("permanent_teachers")) {
			this.setPermanentTeachers(Integer.parseInt(data));
		} else if (field.equals("theses")) {
			this.setTheses(Integer.parseInt(data));
		} else if (field.equals("dissertations")) {
			this.setDissertations(Integer.parseInt(data));
		} else if (field.equals("id_articles")) {
			this.setIdArticles(Integer.parseInt(data));
		} else if (field.equals("id_books")) {
			this.setIdBooks(Integer.parseInt(data));
		} else if (field.equals("published_works")) {
			this.setPublishedWorks(Integer.parseInt(data));
		} else if (field.equals("artistic_production")) {
			this.setArtisticProduction(Integer.parseInt(data));
		} else{
			
		}
		

	}

	@Override
	public ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("id_institution");
		fields.add("id_course");
		fields.add("year");
		fields.add("modality");
		fields.add("master_degree_start_year");
		fields.add("doctorate_start_year");
		fields.add("triennial_evaluation");
		fields.add("permanent_teachers");
		fields.add("theses");
		fields.add("dissertations");
		fields.add("id_articles");
		fields.add("id_books");
		fields.add("published_works");
		fields.add("artistic_production");
		return fields;
	}
	
	public boolean save() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.insertBean(this);
		this.setId(Evaluation.last().getId());
		return result;
	}

	public static Evaluation get(Integer id) throws ClassNotFoundException, SQLException {
		Evaluation result = new Evaluation(id);
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Evaluation) gDB.selectBean(result);
		return result;
	}

	public static ArrayList<Evaluation> getAll()
			throws ClassNotFoundException, SQLException {
		Evaluation type = new Evaluation();
		ArrayList<Evaluation> result = new ArrayList<Evaluation>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectAllBeans(type)) {
			result.add((Evaluation) b);
		}
		return result;
	}

	public static Integer count() throws ClassNotFoundException, SQLException {
		Evaluation type = new Evaluation();
		GenericBeanDAO gDB = new GenericBeanDAO();
		return gDB.countBean(type);
	}

	public static Evaluation first() throws ClassNotFoundException,
			SQLException {
		Evaluation result = new Evaluation();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Evaluation) gDB.firstOrLastBean(result, false);
		return result;
	}

	public static Evaluation last() throws ClassNotFoundException,
			SQLException {
		Evaluation result = new Evaluation();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Evaluation) gDB.firstOrLastBean(result, true);
		return result;
	}

	public static ArrayList<Evaluation> getWhere(String field, String value, boolean like) 
			throws ClassNotFoundException, SQLException {
		Evaluation type = new Evaluation();
		ArrayList<Evaluation> result = new ArrayList<Evaluation>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectBeanWhere(type, field, value, like)) {
			result.add((Evaluation) b);
		}
		return result;
	}
	
	public boolean delete() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.deleteBean(this);
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdInstitution() {
		return idInstitution;
	}

	public void setIdInstitution(Integer idInstitution) {
		this.idInstitution = idInstitution;
	}

	public Integer getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public Integer getMasterDegreeStartYear() {
		return masterDegreeStartYear;
	}

	public void setMasterDegreeStartYear(Integer masterDegreeStartYear) {
		this.masterDegreeStartYear = masterDegreeStartYear;
	}

	public Integer getDoctorateStartYear() {
		return doctorateStartYear;
	}

	public void setDoctorateStartYear(Integer doctorateStartYear) {
		this.doctorateStartYear = doctorateStartYear;
	}

	public Integer getTriennialEvaluation() {
		return triennialEvaluation;
	}

	public void setTriennialEvaluation(Integer triennialEvaluation) {
		this.triennialEvaluation = triennialEvaluation;
	}

	public Integer getPermanentTeachers() {
		return permanentTeachers;
	}

	public void setPermanentTeachers(Integer permanentTeachers) {
		this.permanentTeachers = permanentTeachers;
	}

	public Integer getTheses() {
		return theses;
	}

	public void setTheses(Integer theses) {
		this.theses = theses;
	}

	public Integer getDissertations() {
		return dissertations;
	}

	public void setDissertations(Integer dissertations) {
		this.dissertations = dissertations;
	}

	public Integer getIdArticles() {
		return idArticles;
	}

	public void setIdArticles(Integer idArticles) {
		this.idArticles = idArticles;
	}

	public Integer getIdBooks() {
		return idBooks;
	}

	public void setIdBooks(Integer idBooks) {
		this.idBooks = idBooks;
	}

	public Integer getPublishedWorks() {
		return publishedWorks;
	}

	public void setPublishedWorks(Integer publishedWorks) {
		this.publishedWorks = publishedWorks;
	}

	public Integer getArtisticProduction() {
		return artisticProduction;
	}

	public void setArtisticProduction(Integer artisticProduction) {
		this.artisticProduction = artisticProduction;
	}

}
