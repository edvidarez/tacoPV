package application;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Mesa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7646582535660008680L;
	public ArrayList<Producto> productos;
	//public ObservableList<String> olp = FXCollections.observableArrayList ();
	public int numero ;
	public Mesa(int numero) {
		this.productos = new ArrayList<Producto>();
		this.numero = numero;
		System.out.println("nueva mesa");
	}
	float getCuenta() {
		float cuenta = 0;
		for(Producto p : productos)
		{
			cuenta += p.precio;
		}
		return cuenta;
	}
}
