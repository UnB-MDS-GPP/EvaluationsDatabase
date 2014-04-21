package models;

import java.util.ArrayList;
import java.util.Hashtable;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import libraries.GenericCRUDModel;

public class Institution extends Bean{
	private Integer id;
	private String acronym;


	public Institution() {
		this.id = 0;
		this.identifier = "institution";
		this.relationship = "courses_institutions";
	}
	
	public Institution(Integer id) {
		this.id = id;
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
	
	public static Institution get(Integer id){
		Institution result = new Institution(id);
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			result = (Institution)gDB.selectBean(result);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static ArrayList<Institution> getAll() {
		Institution type = new Institution();
		ArrayList<Institution> result = new ArrayList<Institution>();
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			for(Bean b : gDB.selectAllBeans(type)){
				result.add((Institution)b);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Integer count(){
		Institution type = new Institution();
		int result = 0;
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			result = gDB.countBean(type);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Institution first(){
		Institution result = new Institution();
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			result = (Institution)gDB.firstOrLastBean(result,false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Institution last(){
		Institution result = new Institution();
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			result = (Institution)gDB.firstOrLastBean(result,true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArrayList<Institution> getWhere(String field, String value, boolean like) {
		Institution type = new Institution();
		ArrayList<Institution> result = new ArrayList<Institution>();
		try {
			GenericBeanDAO gDB = new GenericBeanDAO();
			for(Bean b : gDB.selectBeanWhere(type, field, value, like)){
				result.add((Institution)b);
			}
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
