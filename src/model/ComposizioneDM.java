package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComposizioneDM {
	private static final String TABLE_NAME = "composizione";
	
public synchronized void doSave(int idOrdine,int idProdotto,int quant) throws SQLException{
		
		Connection con=null;
		PreparedStatement preparedStatement = null;;
		

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (idordine, idprodotto, quantita) VALUES (?, ?, ?)";
	
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(1,idOrdine);
			preparedStatement.setInt(2, idProdotto);
			preparedStatement.setInt(3, quant);
			
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


public synchronized ArrayList<Composizione> doRetrieveByidOrdine(Composizione compOrdine) throws SQLException{
	Connection con=null;
	Statement stmt=null;
	
	
	ArrayList<Composizione> totOrdini=new ArrayList<Composizione>();
	
	String selectSQL="SELECT * FROM shoes.composizione where idordine="+compOrdine.getIdOrdine();
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			Composizione ordine=new Composizione();
			ordine.setIdOrdine(rs.getInt("idordine"));
			ordine.setIdProdotto(rs.getInt("idprodotto"));
			ordine.setQuantita(rs.getInt("quantita"));
			totOrdini.add(ordine);
		}
	} finally {
		try {
			if(stmt != null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return totOrdini;
	
}



}
