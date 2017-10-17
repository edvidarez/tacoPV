package application;

import java.util.ArrayList;

public class Manager {

	   private static Manager manager = new Manager( );
	   ArrayList<Mesa> Mesas;
	   /* A private Constructor prevents any other
	    * class from instantiating.
	    */
	   private Manager() {
		   Mesas = new ArrayList<Mesa>();
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
				   System.out.println(me.getCuenta());
			   }
			   return true;
		   }
		   return false;
	   }

	  
	}