package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
    	Connection conObj;
    	Statement stObj;
    	PreparedStatement ps = null;
    	public Database() throws SQLException , ClassNotFoundException {
    		Class.forName("com.mysql.jdbc.Driver"); /*Loading Driver class for JDBC*/
    		conObj = DriverManager.getConnection("jdbc:mysql://138.197.202.114:3306/tacos","edvidarez","123123123");
    		stObj = conObj.createStatement();
    	}
    	public int validateUser(String user, String pass,String role) throws Exception {
    		int role_ = -1;
    		System.out.println(role);
    		switch (role) {
    		case "Admin": role_ = 1;
    		break;
    		case "Gerente": role_ = 2;
    		break;
    		case "Empleado": role_ = 3;
    		break;
    		}
    		System.out.println(role_);
    		String query = "select * from user where username = ? and pass = ? and role = ?"; // agregar username en vez de email
    		ps = (PreparedStatement) conObj.prepareStatement(query);
    		ps.setString(1,user);
    		ps.setString(2,pass);
    		ps.setInt(3, role_);
    		ResultSet rs = ps.executeQuery();

    		if(rs.next())
    			return rs.getInt(1);
    		return 0;
    	}
    	public ResultSet getVentas() throws SQLException {
    		String query = "select s.nombre, v.fecha, u.email, v.subtotal,v.iva, v.total from venta v, sucursal s, user u where v.ID_Sucursal = s.ID_Sucursal and v.vendedor = u.ID_User";
    		return  stObj.executeQuery(query);

    	}
    	public void fetchData() throws Exception
    	{
    		String query = "select * from user";
    		ResultSet rs = stObj.executeQuery(query);

    		while(rs.next())
    		{
    			System.out.println("id : "+rs.getInt("ID_User"));
    			System.out.println("email : "+rs.getString("email"));
    			System.out.println("pass : "+rs.getString("pass"));
    			System.out.println("role : "+rs.getInt("role"));
    			System.out.println("RFC : "+rs.getString("RFC"));
    			System.out.println("username : "+rs.getString("username"));
    		}
    	}
    	public String getToday() {
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    		LocalDateTime now = LocalDateTime.now();
    		return dtf.format(now);
    	}
    	public void pagarMesa(Mesa me) throws SQLException {
    		int id_venta = 0;
    		String generatedColumns[] = { "ID_Venta" };
    		String query = "INSERT INTO venta (ID_Sucursal, fecha, vendedor, subTotal, iva, total) values (1,?,?,?,?,?)";
    		ps = (PreparedStatement) conObj.prepareStatement(query,generatedColumns);
    		ps.setString(1, getToday());
    		ps.setInt(2, Session.getInstance().getId());
    		ps.setFloat(3, me.getCuenta());
    		ps.setFloat(4, (float) (me.getCuenta()*0.16));
    		ps.setFloat(5, (float) (me.getCuenta()*1.16));
    		System.out.println(ps);
    		ps.executeUpdate();
    		ResultSet rs = ps.getGeneratedKeys();
    		if (rs.next()) {
    		    id_venta = rs.getInt(1);
    		}
    		System.out.println("El id que se inserto fue: "+id_venta);
    		int i=0;
    		for(Producto p : me.productos)
    		{
    			query = "insert into venta_d (ID_Venta, ID_Producto, cantidad, precio) values(? , ? , ?, ?)";
    			ps = (PreparedStatement) conObj.prepareStatement(query);
        		ps.setInt(1,id_venta);
        		ps.setInt(2,p.idProducto);
        		ps.setInt(3, 1);
        		ps.setFloat(4, p.precio);
        		System.out.println(ps);
        		ps.executeUpdate();
    			i++;
    		}
    	}
    	public ArrayList<Producto> fetchProductos() throws Exception{
    		String query = "select * from productos";
    		ResultSet rs = stObj.executeQuery(query);
    		ArrayList<Producto> productos = new ArrayList<Producto>();
    		while(rs.next())
    		{
    			Producto p = new Producto(rs.getString("descripcion"), rs.getFloat("precio"), rs.getInt("ID_Producto"));
    			productos.add(p);
    		}
    		return productos;
    	}
    public void insertData(String name, int age) throws SQLException 
    {
        if(name!=null && age!=0)
        {
            String query = "insert into user values(\""+name+"\","+age+")";
            int a = stObj.executeUpdate(query);

            if(a == 1)
            {
                System.out.println("Update Successful");
            }
            else
            {
                System.out.println("Update Failed");
            }
        }
    }


    void deleteData(String name) throws Exception 
    {
        String query = "delete from user where name = \""+name+"\"";
        int a = stObj.executeUpdate(query);

        if(a == 1)
        {
                System.out.println("delete Successful");
        }
        else
        {
                System.out.println("deletion Failed");
        }
    }
}