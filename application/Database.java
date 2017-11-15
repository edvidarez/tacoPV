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

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Database extends Thread{
    	Connection conObj;
    	Statement stObj;
    	PreparedStatement ps = null;
    	private  void Setup() throws ClassNotFoundException, SQLException {
    		Class.forName("com.mysql.jdbc.Driver"); /*Loading Driver class for JDBC*/
    		conObj = DriverManager.getConnection("jdbc:mysql://138.197.202.114:3306/tacos","edvidarez","123123123");
    		stObj = conObj.createStatement();
    	}
    	public void run(){
    		try {
				Setup();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	public Database() throws SQLException , ClassNotFoundException {
    		run();
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
    		String query = "select * from user where username = ? and pass = ? and role = ?";
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
    			System.out.println("rfc : "+rs.getInt("RFC"));
    			System.out.println("username : "+rs.getInt("username"));
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
    public void insertUser(String email,String pass,String rfc,String nombre,int rol) throws SQLException 
    {
        if(!email.equals("")&&!pass.equals("")&&!rfc.equals("")&&!nombre.equals(""))
        {
        	String query = "insert into user(email,pass,role,RFC,username) values(?,?,?,?,?)";
        	PreparedStatement q = (PreparedStatement) conObj.prepareStatement(query);  
        	q.setString(1, email);
        	q.setString(2, pass);
        	q.setInt(3, rol);
        	q.setString(4, rfc);
        	q.setString(5, nombre);
        	
        	System.out.println(q);
        	Boolean a = q.execute();
        	//q.executeUpdate();
        	//String query = "insert into user values(select Max(ID_User)+1 from tacos.user,\""+email+"\",\""+pass+"\",\""+rol+"\",\""+rfc+"\",\""+nombre+"\")";
            //int a = stObj.executeUpdate(query);
            if(a)
            {
                System.out.println("Update Failed"); 
            }
            else
            {
                System.out.println("Update Successful");
            }
        }
        else {
        	System.out.println("Verificar datos ingresados");
        }
    }

    public void ModifyUser() {
    	//mete tu codigo Ali
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
	public User getUser(String id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * from user where ID_User = " + id;
		User user = new User();
		ResultSet rs = stObj.executeQuery(query);
		while(rs.next())
		{
			user.setId(rs.getInt("ID_User")); 
			user.setEmail(rs.getString("email")); 
			user.setPass(rs.getString("pass"));
			user.setRole(rs.getInt("role"));
			user.setRfc(rs.getString("RFC"));
			user.setUsername(rs.getString("username"));
		}
		return user;
	}
}