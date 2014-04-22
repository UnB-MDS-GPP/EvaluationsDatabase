package models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Article extends Bean {
	private Integer id;
	private Integer internationals;
	private Integer nationals;
	private Integer locals;

	public Article() {
		this.id = 0;
		this.identifier = "articles";
		this.relationship = "";
	}

	public Article(Integer id) {
		this.id = id;
		this.identifier = "articles";
		this.relationship = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInternationals() {
		return internationals;
	}

	public void setInternationals(Integer internationals) {
		this.internationals = internationals;
	}

	public Integer getNationals() {
		return nationals;
	}

	public void setNationals(Integer nationals) {
		this.nationals = nationals;
	}

	public Integer getLocals() {
		return locals;
	}

	public void setLocals(Integer locals) {
		this.locals = locals;
	}

	public boolean save() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.insertBean(this);
		this.setId(Article.last().getId());
		return result;
	}

	public static Article get(Integer id) throws ClassNotFoundException, SQLException {
		Article result = new Article(id);
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Article) gDB.selectBean(result);
		return result;
	}

	public static ArrayList<Article> getAll()
			throws ClassNotFoundException, SQLException {
		Article type = new Article();
		ArrayList<Article> result = new ArrayList<Article>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectAllBeans(type)) {
			result.add((Article) b);
		}
		return result;
	}

	public static Integer count() throws ClassNotFoundException, SQLException {
		Article type = new Article();
		GenericBeanDAO gDB = new GenericBeanDAO();
		return gDB.countBean(type);
	}

	public static Article first() throws ClassNotFoundException,
			SQLException {
		Article result = new Article();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Article) gDB.firstOrLastBean(result, false);
		return result;
	}

	public static Article last() throws ClassNotFoundException,
			SQLException {
		Article result = new Article();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Article) gDB.firstOrLastBean(result, true);
		return result;
	}

	public static ArrayList<Article> getWhere(String field, String value, boolean like) 
			throws ClassNotFoundException, SQLException {
		Article type = new Article();
		ArrayList<Article> result = new ArrayList<Article>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectBeanWhere(type, field, value, like)) {
			result.add((Article) b);
		}
		return result;
	}
	
	public boolean delete() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.deleteBean(this);
		return result;
	}

	@Override
	public String get(String field) {
		if(field.equals("id")) {
			return Integer.toString(this.getId());
		}
		
		else if(field.equals("internationals")) {
			return Integer.toString(this.getInternationals());
		}
		
		else if (field.equals("nationals")) {
			return Integer.toString(this.getNationals());
		}
		
		else if(field.equals("locals")) {
			return Integer.toString(this.getLocals());
		}
		
		else {
			return "";
		}
	}

	@Override
	public void set(String field, String data) {
		if (field.equals("id")) {
			this.setId(Integer.parseInt(data));
		} 
		
		else if (field.equals("internationals")) {
			this.setInternationals(Integer.parseInt(data));
		}
		
		else if (field.equals("nationals")) {
			this.setNationals(Integer.parseInt(data));
		}
		
		else if (field.equals("locals")) {
			this.setLocals(Integer.parseInt(data));
		}
		
		else {

		}
		
	}

	@Override
	public ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("internationals");
		fields.add("nationals");
		fields.add("locals");
		return fields;
	}
}
