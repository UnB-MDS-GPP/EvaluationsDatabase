package builder;

import java.util.ArrayList;

public class GenerateCRUD {
	
	
	
	public GenerateCRUD() {
		// TODO Auto-generated constructor stub
	}
	
	public int sum(){
		return 3;
	}

	public <T> T getAll(){	
		
		return (T) new GenerateCRUD();
	}
	
}
