package models;

import java.util.ArrayList;
import java.util.Hashtable;

import libraries.GenericCRUDModel;

public class Institution {
	private Integer id;
	private String acronym;
	private ArrayList<Curso> cursos;
	
	public ArrayList<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(ArrayList<Curso> cursos) {
		this.cursos = cursos;
	}

	public Institution() {
		this.id = 0;
		this.cursos = new ArrayList<Curso>();
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	public Integer getId() {
		return id;
	}
	
	public boolean save() {
		boolean result = false;

		Hashtable<String, String> dataInstitution = new Hashtable<String, String>();
		Hashtable<String, String> institutionId = new Hashtable<String, String>();
		dataInstitution.put("sigla", this.getAcronym());
		institutionId.put("id_instituicao", Integer.toString(this.getId()));
		try {
			GenericCRUDModel crud = new GenericCRUDModel();

			if( this.id > 0 ){
				result = crud.update("instituicao", dataInstitution, this.id);
				crud.deleteWhere("cursos_instituicoes", institutionId);
				for(Curso c : this.getCursos()){
					Hashtable<String, String> newData = new Hashtable<String, String>();
					newData.put("id_instituicao", Integer.toString(this.getId()));
					newData.put("id_curso", Integer.toString(c.getId()));
					crud.insert("cursos_instituicoes", newData);
				}
			} else {
				result = crud.insert("instituicao", dataInstitution);
				if (result){
					this.id = Curso.last().getId();
					for(Curso c : this.getCursos()){
						Hashtable<String, String> newData = new Hashtable<String, String>();
						newData.put("id_instituicao", Integer.toString(this.getId()));
						newData.put("id_curso", Integer.toString(c.getId()));
						crud.insert("cursos_instituicoes", newData);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
}
