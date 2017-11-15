package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Cliente  extends ConexionSockets{
	public Cliente() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion
	public void putMesas() {
		try {
	        os = new ObjectOutputStream(cs.getOutputStream());
	        if(Manager.getInstance().Mesas.size()>0)
	        {
				//os.writeObject(mesas);
	        	System.out.println("El cliente envio");
	        	os.writeObject(Manager.getInstance().Mesas);
	        	
	        }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void getMesas() {
		try {
	        //ObjectOutputStream os = new ObjectOutputStream(cs.getOutputStream());
	        is = new ObjectInputStream(cs.getInputStream());
	        
	       
	        Manager.getInstance().Mesas= (ArrayList<Mesa>) is.readObject();
	        System.out.println("El size de las mesas es: "+Manager.getInstance().Mesas.size());
	        for(Mesa m : Manager.getInstance().Mesas)
	        {
	        	for(Producto p : m.productos)
	        	{
	        		System.out.println(p.descripcion+" "+p.cantidad);
	        	}
	        }
	        
            
            //cs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
