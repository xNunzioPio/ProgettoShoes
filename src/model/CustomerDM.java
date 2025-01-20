package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class CustomerDM {
	private static final String TABLE_NAME = "cliente";
	
	public synchronized void doSave(CustomerBean cliente) throws SQLException{
		
		
		Connection con=null;
		Statement stmt=null;
		String insertSQL ="INSERT INTO `shoes`.`cliente` (`idCliente`, `nome`, `cognome`, `dataNascita`, `citta`, `cap`, `indirizzo`, `ntelefono`) VALUES ('"+cliente.getIdCliente()+"', '"+cliente.getNome()+"', '"+cliente.getCognome()+"', '"+cliente.getDataNascita()+"', '"+cliente.getCitta()+"', '"+cliente.getCAP()+"', '"+cliente.getIndirizzo()+"', '"+cliente.getTelefono()+"')";
	
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			stmt.executeUpdate(insertSQL);
			con.commit();
		} 
		finally {
			try {
				if(stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
	
	public synchronized CustomerBean doRetrieveByKey(int code) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		CustomerBean cliente=new CustomerBean();
		
		String selectSQL="SELECT * FROM shoes.cliente where idCliente="+code;
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCognome(rs.getString("cognome"));
				cliente.setDataNascita(rs.getString("dataNascita"));
				cliente.setCitta(rs.getString("citta"));
				cliente.setCAP(rs.getInt("cap"));
				cliente.setIndirizzo(rs.getString("indirizzo"));
				cliente.setTelefono(rs.getString("ntelefono"));
			}
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return cliente;
		
	}
	
	public synchronized ArrayList<Integer> allID() throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		ArrayList<Integer> IDs=new ArrayList<Integer>();
		
		String selectSQL="SELECT idCliente FROM shoes.cliente";
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
				int valore=0;
				valore=rs.getInt("idCliente");
				IDs.add(valore);
			}
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return IDs;
		
	}
	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		int result=0;
		
		String deleteSQL="DELETE FROM `shoes`.`cliente` WHERE (`idCliente` = '"+code+"')";
		
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
	
	public synchronized Collection<CustomerBean> doRetriveAll(String ordinaPer) throws SQLException {
		Connection con=null;
		Statement stmt=null;
		
		Collection<CustomerBean>clienti=new LinkedList<CustomerBean>();
		
		String selectSQL="SELECT * FROM shoes.cliente";
		
		if(ordinaPer !=null && !ordinaPer.equals("")) {
			selectSQL+=" ORDER BY"+ordinaPer;
		}
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
				CustomerBean cliente=new CustomerBean();
				//CREIAMO IL BEAN DEL CLIENTE
				cliente.setIdCliente(rs.getInt("idCliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCognome(rs.getString("cognome"));
				cliente.setDataNascita(rs.getString("dataNascita"));
				cliente.setCitta(rs.getString("citta"));
				cliente.setCAP(rs.getInt("cap"));
				cliente.setIndirizzo(rs.getString("indirizzo"));
				cliente.setTelefono(rs.getString("ntelefono"));
				//LO AGGIUNGIAMO ALLA LISTA DI CLIENTI
				clienti.add(cliente);
			}
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return clienti;	
	}
	
	public synchronized ArrayList<Integer> doRetrieveByName(CustomerBean bean) throws SQLException{
		Connection con=null;
		Statement stmt=null;
		
		ArrayList<Integer> idClienti=new ArrayList<Integer>();
		
		
		String selectSQL="SELECT idCliente FROM "+ CustomerDM.TABLE_NAME + " WHERE nome ='"+bean.getNome()+"' AND cognome ='"+bean.getCognome()+"'";
		
		try {
			con=DriverManagerConnectionPool.getConnection();
			stmt=con.createStatement();
			
			ResultSet rs=stmt.executeQuery(selectSQL);
			
			while(rs.next()) {
				idClienti.add(rs.getInt("idCliente"));
			}
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		return idClienti;
		
	}


}
