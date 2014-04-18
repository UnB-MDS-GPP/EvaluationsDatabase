import libraries.DataBaseStructures;
import libraries.GenericCRUDModel;

import java.util.*;
import models.*;

public class Main {

	public static void main(String[] args) {
		try {
			DataBaseStructures dbStructures = new DataBaseStructures("test");
			dbStructures.initDB();

			Curso curso = Curso.get(1);
			
			if( curso != null ) {
				System.out.println("ID: "+curso.getId());
				System.out.println("Nome: "+curso.getNome());
			} else
				System.out.println("Curso table is empty");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
