package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    	public boolean validateUser(String user, String pass,String role) throws Exception {
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
    		String query = "select * from user where email = ? and pass = ? and role = ?";
    		ps = (PreparedStatement) conObj.prepareStatement(query);
    		ps.setString(1,user);
    		ps.setString(2,pass);
    		ps.setInt(3, role_);
    		ResultSet rs = ps.executeQuery();

    		if(rs.next())
    			return true;
    		return false;
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
    		}
    	}
    	public ArrayList<Producto> fetchProductos() throws Exception{
    		String query = "select * from productos";
    		ResultSet rs = stObj.executeQuery(query);
    		ArrayList<Producto> productos = new ArrayList<Producto>();
    		while(rs.next())
    		{
    			Producto p = new Producto(rs.getString("descripcion"), rs.getFloat("precio"));
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