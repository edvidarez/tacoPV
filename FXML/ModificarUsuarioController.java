package FXML;

import java.util.ArrayList;

import application.Database;
import application.User;

public class ModificarUsuarioController {
	private String id = null;
	private String email;
	private String pass;
	private int role;
	private String rfc;
	private String username;
	ArrayList<User> users = new ArrayList<>();
	
	public void modifyUser() {
		//id == the get text from the textview for the Id
		
		
		
	}
	
	
	public void getUsers() {
		//set modify button and textfields to disabled except for the id one
		
		try {
			if(!id.equals("")&&!id.equals("Ingrese in Id")) {
				Database db = new Database();
				User user = db.getUsers(id);
			}
			else {
				//Give the user warning to enter an id
				id = "Ingrese in Id";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//enable textViews and button
	}
	
	public void populateUsers() {
		
		
	}
	
	
}
