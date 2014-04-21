package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import libraries.DataBaseConnection;

public class GenericBeanDAO extends DataBaseConnection{

	public GenericBeanDAO() throws SQLException, ClassNotFoundException {
		super();
	}
	
	/*public boolean insert(Institution institution) throws SQLException{
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
	*/
	public ArrayList<Bean> selectBeanRelationship(Bean bean, String table) throws SQLException{
		this.openConnection();
		ArrayList<Bean>  beans = new ArrayList<Bean>();
		String sql = "SELECT c.* FROM "+table+" as c, "
                + bean.relationship+" as ci "
                + "WHERE ci.id_"+bean.identifier+"= ? "
                + "AND ci.id_"+table+" = c.id";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setString(1, bean.get(bean.fieldsList().get(0)));
		ResultSet rs = this.pst.executeQuery();
		while(rs.next()){
			Bean object = init(table);
			for(String s : bean.fieldsList()){
				object.set(s, rs.getString(s));
			}
			beans.add(object);
		}
		this.closeConnection();
		return beans;
	}
	
	public boolean insertBean(Bean bean) throws SQLException{
		this.openConnection();
		String replace = "";
		int i=1;
		String sql = "INSERT INTO "+bean.identifier+"(";
		for(String s : bean.fieldsList()){
			sql += s+",";
			replace += "?,";
		}
		sql = sql.substring(0, sql.length()-1);
		replace = replace.substring(0, replace.length()-1);
		sql+=") VALUES("+replace+")";
		this.pst = this.conn.prepareStatement(sql);
		for(String s : bean.fieldsList()){
			this.pst.setString(i,bean.get(s));
			i++;
		}
		int result = this.pst.executeUpdate();
		this.closeConnection();
		return (result == 1) ? true : false;
	}
	
	public boolean addBeanRelationship(Bean parentBean, Bean childBean) throws SQLException{
		this.openConnection();
		String sql = "INSERT INTO "+parentBean.relationship+"(id_"+
					 parentBean.identifier+",id_"+
					 childBean.identifier+") VALUES(?,?)";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setString(1, parentBean.get(parentBean.fieldsList().get(0)));
		this.pst.setString(2, childBean.get(childBean.fieldsList().get(0)));
		int result = this.pst.executeUpdate();
		this.closeConnection();
		return (result == 1) ? true : false;
	}

	public Bean selectBean(Bean bean) throws SQLException{
		this.openConnection();
		Bean result = null;
		String sql = "SELECT * FROM "+bean.identifier +
					 " WHERE "+ bean.fieldsList().get(0)+
					 " = ?";
		this.pst = this.conn.prepareStatement(sql);
		this.pst.setString(1, bean.get(bean.fieldsList().get(0)));
		ResultSet rs = this.pst.executeQuery();
		result = init(bean.identifier);
		for(String s : bean.fieldsList()){
			result.set(s, rs.getString(s));
		}
		this.closeConnection();
		return result;
	}
	
	public ArrayList<Bean> selectAllBeans(Bean type) throws SQLException{
		this.openConnection();
		ArrayList<Bean> beans = new ArrayList<Bean>();
		String sql = "SELECT * FROM "+type.identifier;
		this.pst = this.conn.prepareStatement(sql);
		
		ResultSet rs = this.pst.executeQuery();
		while(rs.next()){
			Bean bean = init(type.identifier);
			for(String s : type.fieldsList()){
				bean.set(s, rs.getString(s));
			}
			beans.add(bean);
		}
		this.closeConnection();
		return beans;
	}
	
	public Bean init(String beanIdentifier){
		Bean object = null;
		if(beanIdentifier.equals("institution")){
			object = new Institution();
		}else if(beanIdentifier.equals("course")){
			//TODO
			return object;
		}
		return object;
	}
	

	
}
