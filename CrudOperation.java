package session.db;
import java.sql.*;

public class CrudOperation {
	private static Connection con=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	public static Connection createConnection() 
	{						
		try{
			Class.forName("com.mysql.jdbc.Driver"); 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/networklogger","root",""); 
			
		}
		catch(ClassNotFoundException|SQLException cse)
		{
			System.out.println(cse);
		}
		return con;
	}
	public static ResultSet getData(String sql)
	{
		try{
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		return rs;
	}
	public static ResultSet getData(String sql,String id)
	{
		con=createConnection();
		try{
		ps=con.prepareStatement(sql);
		ps.setString(1,id);
		rs=ps.executeQuery();
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		return rs;
	}
}
