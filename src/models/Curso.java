package models;
import java.util.ArrayList;
import java.util.Hashtable;

import libraries.GenericCRUDModel;;

public class Curso {
	private int id;
	private String nome;

	public Curso() {
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
	
	public static Curso get(int id) {
		Curso curso = null;
		ArrayList<String> fields = new ArrayList<String>();
		
		fields.add("id");
		fields.add("nome");
		
		try {
			GenericCRUDModel crud = new GenericCRUDModel();
			ArrayList<Hashtable<String, String>> data = crud.select("curso", fields, id);
			
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
}
