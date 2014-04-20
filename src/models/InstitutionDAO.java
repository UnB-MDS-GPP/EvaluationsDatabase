package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import libraries.DataBaseConnection;

public class InstitutionDAO extends DataBaseConnection{

	public InstitutionDAO() throws SQLException, ClassNotFoundException {
		super();
	}
	
	public boolean insert(Institution institution) throws SQLException{
		this.openConnection();
		String sql = "INSERT INTO institution(acronym) VALUES(?)";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setString(1, institution.getAcronym());
		int result = this.pst.executeUpdate();
		this.closeConnection();
		return (result == 1) ? true : false;
	}
	
	public boolean addCourse(Integer id_institution, Integer id_course) throws SQLException{
		this.openConnection();
		String sql = "INSERT INTO courses_institutions(id_institution,id_course) VALUES(?,?)";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setInt(1, id_institution);
		this.pst.setInt(2, id_course);
		int result = this.pst.executeUpdate();
		this.closeConnection();
		return (result == 1) ? true : false;
	}
	
	public ArrayList<Institution> selectAll() throws SQLException{
		this.openConnection();
		ArrayList<Institution> institutions = new ArrayList<Institution>();
		String sql = "SELECT * FROM institution";
		this.pst = this.conn.prepareStatement(sql);
		
		ResultSet rs = this.pst.executeQuery();
		while(rs.next()){
			Institution institution = new Institution();
			institution.setId(rs.getInt("id"));
			institution.setAcronym(rs.getString("acronym"));
			institutions.add(institution);
		}
		this.closeConnection();
		return institutions;
	}
	
	public ArrayList<Course> selectCourses(Integer id_institution) throws SQLException{
		this.openConnection();
		ArrayList<Course>  courses = new ArrayList<Course>();
		String sql = "SELECT c.* FROM course as c, "
                + "courses_institutions as ci "
                + "WHERE ci.id_institution=? "
                + "AND ci.id_course = c.id";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setInt(1, id_institution);
		ResultSet rs = this.pst.executeQuery();
		while(rs.next()){
			Course course = new Course();
			course.setId(rs.getInt("id"));
			course.setName(rs.getString("name"));
			courses.add(course);
		}
		this.closeConnection();
		return courses;
	}
	
}
