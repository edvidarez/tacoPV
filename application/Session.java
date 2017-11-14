package application;

public class Session {

	   private static Session session = new Session( );
	   private static String user;
	   private static int Id;
	
	   
	public static String getUser() {
		return user;
	}

	public  void setUser(String user) {
		Session.user = user;
	}

	public int getRole_() {
		return role_;
	}

	public void setRole_(int role_) {
		Session.role_ = role_;
	}

	public static String getRole() {
		return role;
	}

	public void setRole(String role) {
		Session.role = role;
	}


	private static int role_;
	   private static String role;
	   /* A private Constructor prevents any other
	    * class from instantiating.
	    */
	   private Session() { }

	   /* Static 'instance' method */
	   public static Session getInstance( ) {
	      return session;
	   }

	   
	   public void describeUser( ) {
	      System.out.println("The user is:"+user+" and its role is "+role);
	   }

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	}