package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class OrdineDM {
	
	private static final String TABLE_NAME = "ordine";
		
public synchronized void doSave(OrdineBean ordine) throws SQLException{
		
		Connection con=null;
		PreparedStatement preparedStatement = null;;
		

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (idcliente, data, iva, idpagamento, idspedizione) VALUES (?, ?, ?, ?, ?)";
	
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(1, ordine.getIdCliente());
			preparedStatement.setString(2, ordine.getData());
			preparedStatement.setInt(3, ordine.getIva());
			preparedStatement.setInt(4, ordine.getIdPagamento());
			preparedStatement.setInt(5, ordine.getIdSpedizione());
			
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

public synchronized int lastID() throws SQLException{
	Connection con=null;
	Statement stmt=null;
	int valore=0;
	
	String selectSQL="SELECT max(idordine) FROM shoes.ordine";
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			valore=rs.getInt("max(idordine)");
		}
	} finally {
		try {
			if(stmt != null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return valore;
	
}
	
public synchronized OrdineBean doRetrieveByid(int id) throws SQLException{
	Connection con=null;
	Statement stmt=null;
	
	OrdineBean ordine=new OrdineBean();
	
	String selectSQL="SELECT * FROM shoes.ordine where idordine="+id;
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			ordine.setIdOrdine(rs.getInt("idordine"));
			ordine.setIdCliente(rs.getInt("idcliente"));
			ordine.setData(rs.getString("data"));
			ordine.setIva(rs.getInt("iva"));
			ordine.setIdPagamento(rs.getInt("idpagamento"));
			ordine.setIdSpedizione(rs.getInt("idspedizione"));
		}
	} finally {
		try {
			if(stmt != null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return ordine;
	
}

public synchronized boolean doDelete(int id) throws SQLException {
	Connection con=null;
	Statement stmt=null;
	
	int result=0;
	
	String deleteSQL="DELETE FROM `shoes`.`ordine` WHERE (`idordine` = '"+id+"')";
	
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

public synchronized Collection<OrdineBean> doRetrieveAll() throws SQLException {
	Connection con=null;
	Statement stmt=null;
	
	Collection<OrdineBean>ordini=new LinkedList<OrdineBean>();
	
	String selectSQL="SELECT * FROM shoes.ordine";
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			OrdineBean ordine=new OrdineBean();
			ordine.setIdOrdine(rs.getInt("idordine"));
			ordine.setIdCliente(rs.getInt("idcliente"));
			ordine.setData(rs.getString("data"));
			ordine.setIva(rs.getInt("iva"));
			ordine.setIdPagamento(rs.getInt("idpagamento"));
			ordine.setIdSpedizione(rs.getInt("idspedizione"));
			ordini.add(ordine);
		}
	} finally {
		try {
			if(stmt!=null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return ordini;	
}

public synchronized ArrayList<OrdineBean> doRetrieveByidCliente2(int id) throws SQLException{
	Connection con=null;
	Statement stmt=null;
	
	OrdineBean ordine=new OrdineBean();
	ArrayList<OrdineBean> ordini=new ArrayList<OrdineBean>();
	
	String selectSQL="SELECT * FROM shoes.ordine where idcliente="+id;
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			ordine.setIdOrdine(rs.getInt("idordine"));
			ordine.setIdCliente(rs.getInt("idcliente"));
			ordine.setData(rs.getString("data"));
			ordine.setIva(rs.getInt("iva"));
			ordine.setIdPagamento(rs.getInt("idpagamento"));
			ordine.setIdSpedizione(rs.getInt("idspedizione"));
			ordini.add(ordine);
		}
	} finally {
		try {
			if(stmt != null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return ordini;
	
}

public synchronized ArrayList<OrdineBean> doRetrieveByidCliente(int id,String data_dal,String data_al) throws SQLException{
	Connection con=null;
	Statement stmt=null;
	
	ArrayList<OrdineBean> ordini= new ArrayList<OrdineBean>();
	
	String selectSQL="SELECT * FROM shoes.ordine where idcliente="+id+" AND data>='"+data_dal+"' AND data<='"+data_al+"'";
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			OrdineBean ordine=new OrdineBean();
			ordine.setIdOrdine(rs.getInt("idordine"));
			ordine.setIdCliente(rs.getInt("idcliente"));
			ordine.setData(rs.getString("data"));
			ordine.setIva(rs.getInt("iva"));
			ordine.setIdPagamento(rs.getInt("idpagamento"));
			ordine.setIdSpedizione(rs.getInt("idspedizione"));
			ordini.add(ordine);
		}
	} finally {
		try {
			if(stmt != null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return ordini;
	
}

}
