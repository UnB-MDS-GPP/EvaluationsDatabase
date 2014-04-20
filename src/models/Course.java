package models;

import java.util.ArrayList;
import java.util.Hashtable;

import libraries.GenericCRUDModel;;

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


	public boolean save() {
		boolean result = false;

		Hashtable<String, String> data = new Hashtable<String, String>();
		data.put("name", this.getName());

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			if( this.id > 0 )
				result = crud.update("course", data, this.id);
			else {
				result = crud.insert("course", data);

				if (result)
					this.id = Course.last().getId();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean delete() {
		boolean result = false;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			if( this.id > 0 ) {
				result = crud.delete("course", this.id);
				this.id = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public static ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		
		fields.add("id");
		fields.add("name");

		return fields;
	}


	public static Course get(int id) {
		Course course = null;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();
			ArrayList<Hashtable<String, String>> data = crud.select("course", Course.fieldsList(), id);

			if( data.size() == 1 ) {
				String idcourse = data.get(0).get("id");
				String namecourse = data.get(0).get("name");

				course = new Course(Integer.parseInt(idcourse), namecourse);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return course;
	}

	public static ArrayList<Course> getAll() {
		ArrayList<Course> result = new ArrayList<Course>();

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			ArrayList<Hashtable<String, String>> selectRsult = crud.select("course", Course.fieldsList());

			if( selectRsult.size() > 0 ) {
				Hashtable<String, String> row;

				for(int i = 0; i < selectRsult.size(); i++) {
					row = selectRsult.get(i);

					result.add(new Course(Integer.parseInt(row.get("id")), row.get("name")));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public static int count() {
		int count = 0;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();
			count = crud.count("course");
		} catch(Exception e) {
			e.printStackTrace();
		}

		return count;
	}


	public static Course first() {
		Course course = null;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			Hashtable<String, String> row = crud.firstOrLast("course", Course.fieldsList(), false);

			course = new Course(Integer.parseInt(row.get("id")), row.get("name"));

		} catch(Exception e) {
			e.printStackTrace();
		}
		return course;
	}


	public static Course last() {
		Course course = null;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			Hashtable<String, String> row = crud.firstOrLast("course", Course.fieldsList(), true);

			course = new Course(Integer.parseInt(row.get("id")), row.get("name"));

		} catch(Exception e) {
			e.printStackTrace();
		}
		return course;
	}


	public static ArrayList<Course> getWhere(String key, String value, boolean like) {
		ArrayList<Course> result = new ArrayList<Course>();
		String conditions;

		if( like == true )
			conditions = key+" LIKE '%"+value+"%'";
		else
			conditions = key+" = '"+value+"'";

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			ArrayList<Hashtable<String, String>> selectRsult = crud.selectWhere("course", Course.fieldsList(), conditions);

			if( selectRsult.size() > 0 ) {
				Hashtable<String, String> row;

				for(int i = 0; i < selectRsult.size(); i++) {
					row = selectRsult.get(i);

					result.add(new Course(Integer.parseInt(row.get("id")), row.get("name")));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
