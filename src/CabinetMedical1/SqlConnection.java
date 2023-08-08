package CabinetMedical1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlConnection {
	Connection conn = null;
	Statement stmt=null;
	ResultSet rs= null;
	public static Connection dbConnector(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","SYSTEM","Nanou2000");
			return conn;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
