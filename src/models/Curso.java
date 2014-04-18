package models;

import java.util.ArrayList;
import java.util.Hashtable;

import libraries.GenericCRUDModel;;

public class Curso {
	private int id;
	private String nome;

	public Curso() {
		this.id = 0;
	}
	
	public Curso(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public boolean save() {
		boolean result = false;

		Hashtable<String, String> data = new Hashtable<String, String>();
		data.put("nome", this.getNome());

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			if( this.id > 0 )
				result = crud.update("curso", data, this.id);
			else
				result = crud.insert("curso", data);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static ArrayList<String> fieldsList() {
		ArrayList<String> fields = new ArrayList<String>();
		
		fields.add("id");
		fields.add("nome");

		return fields;
	}


	public static Curso get(int id) {
		Curso curso = null;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();
			ArrayList<Hashtable<String, String>> data = crud.select("curso", Curso.fieldsList(), id);

			if( data.size() == 1 ) {
				String idCurso = data.get(0).get("id");
				String nomeCurso = data.get(0).get("nome");

				curso = new Curso(Integer.parseInt(idCurso), nomeCurso);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return curso;
	}

	public static ArrayList<Curso> getAll() {
		ArrayList<Curso> result = new ArrayList<Curso>();

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			ArrayList<Hashtable<String, String>> selectRsult = crud.select("curso", Curso.fieldsList());

			if( selectRsult.size() > 0 ) {
				Hashtable<String, String> row;

				for(int i = 0; i < selectRsult.size(); i++) {
					row = selectRsult.get(i);

					result.add(new Curso(Integer.parseInt(row.get("id")), row.get("nome")));
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
			count = crud.count("curso");
		} catch(Exception e) {
			e.printStackTrace();
		}

		return count;
	}


	public static Curso first() {
		Curso curso = null;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			Hashtable<String, String> row = crud.firstOrLast("curso", Curso.fieldsList(), false);

			curso = new Curso(Integer.parseInt(row.get("id")), row.get("nome"));

		} catch(Exception e) {
			e.printStackTrace();
		}
		return curso;
	}


	public static Curso last() {
		Curso curso = null;

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			Hashtable<String, String> row = crud.firstOrLast("curso", Curso.fieldsList(), true);

			curso = new Curso(Integer.parseInt(row.get("id")), row.get("nome"));

		} catch(Exception e) {
			e.printStackTrace();
		}
		return curso;
	}


	public static ArrayList<Curso> getWhere(String key, String value, boolean like) {
		ArrayList<Curso> result = new ArrayList<Curso>();
		String conditions;

		if( like == true )
			conditions = key+" LIKE '%"+value+"%'";
		else
			conditions = key+" = '"+value+"'";

		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			ArrayList<Hashtable<String, String>> selectRsult = crud.selectWhere("curso", Curso.fieldsList(), conditions);

			if( selectRsult.size() > 0 ) {
				Hashtable<String, String> row;

				for(int i = 0; i < selectRsult.size(); i++) {
					row = selectRsult.get(i);

					result.add(new Curso(Integer.parseInt(row.get("id")), row.get("nome")));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
