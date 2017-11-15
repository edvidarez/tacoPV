package application;

import java.io.Serializable;

public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String descripcion;
	public String img;
	public float precio;
	public int idProducto;
	public int cantidad = 0;
	public Producto(String Descripcion, float Precio, int idProducto, String img) {
		this.descripcion = Descripcion;
		this.precio = Precio;
		this.idProducto = idProducto;
		this.img = img;
	}
}
