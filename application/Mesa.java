package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Mesa {
	public ArrayList<Producto> productos;
	public ObservableList<String> olp = FXCollections.observableArrayList ();
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
