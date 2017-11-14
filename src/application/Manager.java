package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Manager {

	   private static Manager manager = new Manager( );
	   public ObservableList<Button> olb = FXCollections.observableArrayList ();
	   

	   public int nMesas = 1;
	   
	   public ArrayList<Mesa> Mesas = new ArrayList<Mesa>();
	   public Mesa currentMesa = null; 
	   /* A private Constructor prevents any other
	    * class from instantiating.
	    */
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

		   for(Mesa me : Mesas) {

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