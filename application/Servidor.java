package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Servidor extends ConexionSockets{
	public Servidor(String tipo) throws IOException {
		super(tipo);
	}
	public void listenForChanges() {
		System.out.println("ListenForChanges Enable");
		while(true)
			try {
				

				is = new ObjectInputStream(cs.getInputStream());
				ArrayList<Mesa> isMesa;
				System.out.println("Cliente Actualizo Mesas");
						isMesa = ((ArrayList<Mesa>)is.readObject());
						mesas = isMesa;
			}
		catch(Exception e) {
			//e.printStackTrace();
		}
	}
	public void startServer() {
        
		System.out.println("Inicializando Server...");	
		while(true)
		try {
			cs = ss.accept();
			System.out.println("Acept");
			os = new ObjectOutputStream(cs.getOutputStream());
			//ObjectInputStream is = new ObjectInputStream(cs.getInputStream());
			
			
			System.out.println("Cliente en linea");
			
			//Manager m = (Manager) is.readObject();
			os.writeObject(mesas);
			//cs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void setManager(Manager m)
	{
		mesas = m.Mesas;
		System.out.println("Se actualizo el manager con "+(mesas.size())+" mesas");
	}
		
	
}
