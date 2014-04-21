package models;

import java.sql.SQLException;
import java.util.ArrayList;

import models.DAO.CourseDAO;

public class Course {
	private Integer id;
	private String name;

	public Course() {
		this.id = 0;
	}
	
	public Course(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean save() throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.insert(this);
	}


	public static Course get(int id) throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.getCourse(id);
	}


	public static ArrayList<Course> getAll() throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.selectAll();
	}


	public static int count() throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.count();
	}


	public static Course first() throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.firstOrLast(false);
	}


	public static Course last() throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.firstOrLast(true);
	}


	public static ArrayList<Course> getWhere(String key, String value, boolean use_like) throws ClassNotFoundException, SQLException {
		CourseDAO cDB = new CourseDAO();
		return cDB.getCourseWhere(key, value, use_like);
	}
}
