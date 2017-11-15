package FXML;

import application.Database;

public class ModificarUsuarioController {

	
	
	
	
	public void modifyUser() {
		//id == the get text from the textview for the Id
		String id;
		if(!id.equals("")&&!id.equals("Ingrese in Id")) {
			Database db = new Database();
			db.modifyUser(email, pass, rfc, nombre, r);
		}
		else {
			//Give the user warning to enter an id
		}
		
	}
	
	
	public void getUser() {
		String id;
		try {
		if(!id.equals("")&&!id.equals("Ingrese in Id")) {
			Database db = new Database();
			User user = db.getUser(id);
		}
		else {
			//Give the user warning to enter an id
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
