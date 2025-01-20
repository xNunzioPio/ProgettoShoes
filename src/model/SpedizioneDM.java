package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SpedizioneDM {
	private static final String TABLE_NAME = "spedizione";
	
	public synchronized void doSave(SpedizioneBean  spedizione) throws SQLException{
			
			Connection con=null;
			PreparedStatement preparedStatement = null;
			

			String insertSQL = "INSERT INTO " + TABLE_NAME
					+ " (idspedizione, data, indirizzo, citta, cap) VALUES (?, ?, ?, ?, ?)";
		
			try {
				con=DriverManagerConnectionPool.getConnection();
				preparedStatement = con.prepareStatement(insertSQL);
				preparedStatement.setInt(1, spedizione.getIdSpedizione());
				preparedStatement.setString(2, spedizione.getDate());
				preparedStatement.setString(3, spedizione.getIndirizzo());
				preparedStatement.setString(4, spedizione.getCitta());
				preparedStatement.setInt(5, spedizione.getCap());
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
}
