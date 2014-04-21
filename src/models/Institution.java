package models;

import java.util.ArrayList;
import java.util.Hashtable;

import libraries.GenericCRUDModel;

public class Institution extends Bean{
	private Integer id;
	private String acronym;


	public Institution() {
		this.id = 0;
		this.identifier = "institution";
		this.relationship = "courses_institutions";
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public boolean save() {
		boolean result = false;
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			result = gDB.insertBean(this);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public Institution get(Integer id){
		Institution result = new Institution();
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			result = (Institution)gDB.selectBean(this);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public String get(String field) {
		if(field.equals("id")){
			return Integer.toString(this.getId());
		}else if(field.equals("acronym")){
			return this.getAcronym();
		}else {
		return "";
		}
	}

	@Override
	public void set(String field, String data) {
		if(field.equals("id")){
			this.setId(Integer.parseInt(data));
		}else if(field.equals("acronym")){
			this.setAcronym(data);
		}else {
		
		}
		
	}

	@Override
	public ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("id");
		fields.add("acronym");
		return fields;
	}
	
	
}
