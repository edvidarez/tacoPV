package application;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Manager implements Serializable {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static Manager manager = new Manager( );
		public static void setManager(Manager m )
		{
			manager = m;
		}
	   public ObservableList<Button> olb = FXCollections.observableArrayList ();
	   public Servidor server;

	   public int nMesas = 1;
	   
	   public ArrayList<Mesa> Mesas = new ArrayList<Mesa>();
	   public Mesa currentMesa = null; 
	   /* A private Constructor prevents any other
	    * class from instantiating.
	    */
	   public void startServer()
	   {
		   try {
				server = new Servidor("Servidor");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   private Manager() {
		   
	   }

	   /* Static 'instance' method */
	   public static Manager getInstance( ) {
	      return manager;
	   }
	   public Boolean newMesa(int numero) {
		   for(Mesa me : Mesas) {
			   if(me.numero == numero)
				   return false;
		   }
		   Mesa m = new Mesa(numero);
		   Mesas.add(m);
		   return true;
	   }
	   public Boolean pagarMesa(int numero){
			System.out.println("CLOSEEEEEEEE");
			System.out.println(Mesas.size());
		   for(Mesa me : Mesas) {
			   System.out.println(me.numero);
			   if(me.numero == numero)
			   {
				   System.out.println("El total a pagar es: "+me.getCuenta());
				   
				   try {
					Database d = new Database();
					d.pagarMesa(me);
					return true;
				} catch (SQLException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
			   } 
		   }
		   return false;
	   }

	  
	}