package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ControlloEmail {

	public synchronized int doControl(String email) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		ResultSet result=null;
		
		String selectSQL="SELECT FROM `shoes`.`utente` WHERE (`EMAIL` = '"+email+"')";
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			result=stmt.executeQuery(selectSQL);
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		if (result==null)
			return 0;
		else
			return 1;
	}
	



}
