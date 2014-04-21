package models.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import libraries.DataBaseConnection;
import models.Course;
import models.Institution;

public class CourseDAO extends DataBaseConnection {

	public CourseDAO() throws SQLException, ClassNotFoundException {
		super();
	}

	public Course getCourse(Integer id_course) throws SQLException {
		String sql = "SELECT * FROM course WHERE id=?";
		Course course = null;

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setInt(1, id_course);

		ResultSet rs = this.pst.executeQuery();
		if(rs.next()) {
			course = new Course();
			course.setId(rs.getInt("id"));
			course.setName(rs.getString("name"));
		}
		this.closeConnection();

		return course;
	}

	public boolean insert(Course course) throws SQLException {
		String sql = "INSERT INTO course(name) VALUES(?)";

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setString(1, course.getName());
		int result = this.pst.executeUpdate();
		this.closeConnection();

		return (result == 1) ? true : false;
	}

	public boolean addInstitution(Integer id_institution, Integer id_course) throws SQLException{
		String sql = "INSERT INTO courses_institutions(id_institution,id_course) VALUES(?,?)";

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setInt(1, id_institution);
		this.pst.setInt(2, id_course);
		int result = this.pst.executeUpdate();
		this.closeConnection();

		return (result == 1) ? true : false;
	}

	public ArrayList<Course> selectAll() throws SQLException {
		ArrayList<Course> courses = new ArrayList<Course>();
		String sql = "SELECT * FROM course";

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);

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

	public ArrayList<Institution> selectInstitutions(Integer id_course) throws SQLException{
		ArrayList<Institution>  institutions = new ArrayList<Institution>();
		String sql = "SELECT i.* FROM institution as i, "
                + "courses_institutions as ci "
                + "WHERE ci.id_course=? "
                + "AND ci.id_institution = i.id";

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setInt(1, id_course);

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

	public Integer count() throws SQLException {
		Integer count = 0;
		String sql = "SELECT COUNT(*) FROM course";

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);

		ResultSet rs = this.pst.executeQuery();
		if(rs.next())
			count = rs.getInt(1);

		this.closeConnection();

		return count;
	}

	public Course firstOrLast(boolean last) throws SQLException {
		Course course = null;
		String sql = "SELECT * FROM course ORDER BY id";

		if( !last )
			 sql += " LIMIT 1";
		else
			sql += " DESC LIMIT 1";
		
		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);
		ResultSet rs = this.pst.executeQuery();

		if(rs.next()) {
			course = new Course();
			course.setId(rs.getInt("id"));
			course.setName(rs.getString("name"));
		}
		this.closeConnection();

		return course;
	}

	public ArrayList<Course> getCourseWhere(String key, String value, boolean use_like) throws SQLException {
		ArrayList<Course> courses = new ArrayList<Course>();
		String sql = "SELECT * FROM course WHERE";

		if( !use_like )
			sql += "?=?";
		else
			sql += "? like ?";

		this.openConnection();
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setString(1, key);
		this.pst.setString(2, "%"+value+"%");

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
