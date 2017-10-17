package application;

import java.util.ArrayList;

public class Mesa {
	ArrayList<Producto> productos;
	int numero ;
	public Mesa(int numero) {
		this.productos = new ArrayList<Producto>();
		this.numero = numero;
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
