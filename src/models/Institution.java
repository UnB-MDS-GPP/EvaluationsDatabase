package models;

import java.util.ArrayList;
import java.util.Hashtable;

import libraries.GenericCRUDModel;

public class Institution {
	private Integer id;
	private String acronym;


	public Institution() {
		this.id = 0;
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
			InstitutionDAO iDB = new InstitutionDAO();
			result = iDB.insert(this);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
}
