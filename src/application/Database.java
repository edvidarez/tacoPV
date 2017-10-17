package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    	Connection conObj;
    	Statement stObj;

    	public Database() throws SQLException , ClassNotFoundException {
    		Class.forName("com.mysql.jdbc.Driver"); /*Loading Driver class for JDBC*/
    		conObj = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb","root","");
    		stObj = conObj.createStatement();
    	}

    	public void fetchData() throws Exception
    	{
    		String query = "select * from user";
    		ResultSet rs = stObj.executeQuery(query);

    		while(rs.next())
    		{
    			System.out.println("Name : "+rs.getString("name"));
    			System.out.println("age : "+rs.getInt("age"));
    		}
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