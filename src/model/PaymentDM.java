package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public class PaymentDM {
	
	private static final String TABLE_NAME = "pagamento";
		
public synchronized void doSave(PaymentBean  payment) throws SQLException{
		
		Connection con=null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (idpagamento, data, importo, iva) VALUES (?, ?, ?, ?)";
	
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(1, payment.getIdpagamento());
			preparedStatement.setString(2, payment.getDate());
			preparedStatement.setDouble(3, payment.getImporto());
			preparedStatement.setDouble(4, payment.getIva());
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

