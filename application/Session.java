package application;

import java.io.IOException;
import java.util.Locale;

public class Session {

	   private static Session session = new Session( );
	   private static String user;
	   private static int Id;
	   public static Cliente cliente;
	   public static Locale locale;
	   public static String resourcesLocation;
	   
	public static String getUser() {
		return user;
	}

	public  void setUser(String user) {
		Session.user = user;
	}

	public static int getRole_() {
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
	   private Session() { 
		  locale = new Locale("ES");
		  String resourcesLocation = "i18n.mensajes";
		  Session.resourcesLocation = resourcesLocation;
	   }

	   /* Static 'instance' method */
	   public static Session getInstance( ) {
	      return session;
	   }

	   
	   public void describeUser( ) {
	      System.out.println("The user is:"+user+" and its role is "+role);
	   }
	   
	   public void startClient() {
		   try {
				cliente = new Cliente();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	}
