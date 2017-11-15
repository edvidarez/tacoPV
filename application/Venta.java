package application;

public class Venta {
	int sucursal;
	String Fecha,Empleado;
	float subtotal,iva,total;
	public Venta(int s,String f,String e, float sub, float iva, float total) {
		this.sucursal = s;
		this.Fecha = f;
		this.Empleado = e;
		this.subtotal = sub;
		this.iva = iva;
		this.total = total;
	}
}
