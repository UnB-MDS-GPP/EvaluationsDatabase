package models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Book extends Bean {
	private Integer id;
	private Integer integralText;
	private Integer chapters;
	private Integer collections;
	private Integer entries;

	public Book() {
		this.id = 0;
		this.identifier = "books";
		this.relationship = "";
	}

	public Book(Integer id) {
		this.id = id;
		this.identifier = "books";
		this.relationship = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIntegralText() {
		return integralText;
	}

	public void setIntegralText(Integer integralText) {
		this.integralText = integralText;
	}

	public Integer getChapters() {
		return chapters;
	}

	public void setChapters(Integer chapters) {
		this.chapters = chapters;
	}

	public Integer getCollections() {
		return collections;
	}

	public void setCollections(Integer collections) {
		this.collections = collections;
	}

	public Integer getEntries() {
		return entries;
	}

	public void setEntries(Integer entries) {
		this.entries = entries;
	}

	public boolean save() throws ClassNotFoundException, SQLException {
		boolean result = false;
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = gDB.insertBean(this);
		this.setId(Book.last().getId());
		return result;
	}

	public static Book get(Integer id) throws ClassNotFoundException, SQLException {
		Book result = new Book(id);
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Book) gDB.selectBean(result);
		return result;
	}

	public static ArrayList<Book> getAll()
			throws ClassNotFoundException, SQLException {
		Book type = new Book();
		ArrayList<Book> result = new ArrayList<Book>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectAllBeans(type)) {
			result.add((Book) b);
		}
		return result;
	}

	public static Integer count() throws ClassNotFoundException, SQLException {
		Book type = new Book();
		GenericBeanDAO gDB = new GenericBeanDAO();
		return gDB.countBean(type);
	}

	public static Book first() throws ClassNotFoundException,
			SQLException {
		Book result = new Book();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Book) gDB.firstOrLastBean(result, false);
		return result;
	}

	public static Book last() throws ClassNotFoundException,
			SQLException {
		Book result = new Book();
		GenericBeanDAO gDB = new GenericBeanDAO();
		result = (Book) gDB.firstOrLastBean(result, true);
		return result;
	}

	public static ArrayList<Book> getWhere(String field, String value, boolean like) 
			throws ClassNotFoundException, SQLException {
		Book type = new Book();
		ArrayList<Book> result = new ArrayList<Book>();
		GenericBeanDAO gDB = new GenericBeanDAO();
		for (Bean b : gDB.selectBeanWhere(type, field, value, like)) {
			result.add((Book) b);
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
		
		else if(field.equals("integral_text")) {
			return Integer.toString(this.getIntegralText());
		}
		
		else if (field.equals("chapters")) {
			return Integer.toString(this.getChapters());
		}
		
		else if(field.equals("collections")) {
			return Integer.toString(this.getCollections());
		}
		
		else if(field.equals("entries")) {
			return Integer.toString(this.getEntries());
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
		
		else if (field.equals("integral_text")) {
			this.setIntegralText(Integer.parseInt(data));
		}
		
		else if (field.equals("chapters")) {
			this.setChapters(Integer.parseInt(data));
		}
		
		else if (field.equals("collections")) {
			this.setCollections(Integer.parseInt(data));
		}
		
		else if (field.equals("entries")) {
			this.setEntries(Integer.parseInt(data));
		}
		
		else {

		}
		
	}

	@Override
	public ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("integral_text");
		fields.add("chapters");
		fields.add("collections");
		fields.add("entries");
		return fields;
	}
}
