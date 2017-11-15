package application;


public class UserTableView {
	
	private int ID_User ;
	private String email; 
	private int role ;
	private String RFC; 
	private  String username;
	
	
	public UserTableView() {
		
	}
	
	

	public int getID_User() {
		return ID_User;
	}



	public String getEmail() {
		return email;
	}



	public int getRole() {
		return role;
	}



	public String getRFC() {
		return RFC;
	}



	public String getUsername() {
		return username;
	}



	public void setID_User(int id) {
		this.ID_User =id;
		
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	public void setRole(int role) {
		// TODO Auto-generated method stub
		this.role = role;
	}

	public void setRFC(String rfc) {
		// TODO Auto-generated method stub
		this.RFC = rfc;
	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}
	
	
	
}
