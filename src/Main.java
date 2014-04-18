import libraries.DataBaseStructures;
import libraries.GenericCRUDModel;

import java.util.*;
import models.*;

public class Main {

	public static void main(String[] args) {
		try {
			DataBaseStructures dbStructures = new DataBaseStructures("test");
			dbStructures.initDB();

			ArrayList<Curso> list = Curso.getAll();
			
			System.out.println("TOTAL: "+list.size());
			
			for(int i = 0; i < list.size(); i++) {
				Curso curso = list.get(i);
				
				System.out.println("\nID: "+curso.getId());
				System.out.println("Nome: "+curso.getNome());
			}
			
			//System.out.println("First: "+Curso.first().getNome());
			//System.out.println("Last: "+Curso.last().getNome());

			System.out.println(Curso.getWhere("nome", "AAA", false));
			System.out.println(Curso.getWhere("nome", "A", true));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
