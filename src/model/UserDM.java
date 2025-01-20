package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class UserDM {
	
	private static final String TABLE_NAME = "utente";
	
public synchronized void doSave(UserBean user) throws SQLException{
		
		Connection con=null;
		PreparedStatement preparedStatement = null;;
		

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (IDUTENTE, ROLE, EMAIL, PASSWORD) VALUES (?, ?, ?, ?)";
	
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(1, user.getIdUser());
			preparedStatement.setString(2, user.getRole());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			
			preparedStatement.executeUpdate();
			
			con.commit();
		} 
		finally {
			try {
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
	
	public synchronized UserBean doRetrieveByKey(int code) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		UserBean user=new UserBean();
		
		String selectSQL="SELECT * FROM shoes.utente where idutente="+code;
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
				user.setIdUser(rs.getInt("idutente"));
				user.setRole(rs.getString("role"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				
			}
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return user;
		
	}
	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		int result=0;
		
		String deleteSQL="DELETE FROM `shoes`.`utente` WHERE (`idutente` = '"+code+"')";
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			result=stmt.executeUpdate(deleteSQL);
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return (result!=0);
	}
	
	public synchronized Collection<UserBean> doRetriveAll(String ordinaPer) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		Collection<UserBean>utenti=new LinkedList<UserBean>();
		
		String selectSQL="SELECT * FROM shoes.utente";
		
		if(ordinaPer !=null && !ordinaPer.equals("")) {
			selectSQL+=" ORDER BY"+ordinaPer;
		}
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
				UserBean user=new UserBean();
				//CREIAMO IL BEAN DEL UTENTE
				user.setIdUser(rs.getInt("idutente"));
				user.setRole(rs.getString("role"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				//LO AGGIUNGIAMO ALLA LISTA DI UTENTI
				utenti.add(user);
			}
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return utenti;	
	}
	
	public synchronized boolean serachEmail(String email) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		ResultSet result;
		
		String selectSQL="SELECT email FROM `shoes`.`utente` WHERE (`email` = '"+email+"')";
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			result=stmt.executeQuery(selectSQL);
			String email2="";
			while(result.next()) {
				email2=result.getString("email");
			}
			if(email2.equalsIgnoreCase(email))
				return true;
			else
				return false;
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
	
	public synchronized UserBean searchUser(UserBean user) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		ResultSet result;
		
		String selectSQL="SELECT * FROM utente where email='"+user.getEmail()+"' and password='"+user.getPassword()+"'";
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			result=stmt.executeQuery(selectSQL);
			UserBean userCercato=new UserBean();
			userCercato.setIdUser(-1);
			while(result.next()) {
				userCercato.setIdUser(result.getInt("idutente"));
				userCercato.setRole(result.getString("role"));
				userCercato.setEmail(result.getString("email"));
				userCercato.setPassword(result.getString("password"));
			}
			return userCercato;
		}
		finally {
			try {
				if(stmt != null)
					stmt.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
		
	
	


}
