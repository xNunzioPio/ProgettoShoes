package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;





public class ProductModelM implements ProductModel{
	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ItemBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductModelM.TABLE_NAME
				+ " (NOME, DESCRIZIONE, PREZZO, IMG, IDCATEGORIA, QUANTITA) VALUES (?, ?, ?, ?,?,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setString(2, product.getDescrizione());
			preparedStatement.setDouble(3, product.getPrezzo());
			preparedStatement.setObject(4, product.getImg());
			preparedStatement.setInt(5, product.getCategoria());
			preparedStatement.setInt(6, product.getQuantita());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public synchronized ItemBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ItemBean bean = new ItemBean();

		String selectSQL = "SELECT * FROM " + ProductModelM.TABLE_NAME + " WHERE idprodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_Prodotto(rs.getString("idprodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setImg(rs.getString("img"));
				bean.setCategoria(rs.getInt("idcategoria"));
				bean.setQuantita(rs.getInt("quantita"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
	
	public synchronized ArrayList<ItemBean> doRetrieveByCategory(int category) throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		ArrayList<ItemBean> prodotti=new ArrayList<ItemBean>();
		ItemBean bean = new ItemBean();

		String selectSQL = "SELECT * FROM " + ProductModelM.TABLE_NAME + " WHERE idcategoria = "+category;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			stmt=connection.createStatement();
			ResultSet rs = stmt.executeQuery(selectSQL);

			while (rs.next()) {
				bean.setId_Prodotto(rs.getString("idprodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setImg(rs.getString("img"));
				bean.setCategoria(rs.getInt("idcategoria"));
				bean.setQuantita(rs.getInt("quantita"));
				prodotti.add(bean);
			}

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return prodotti;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModelM.TABLE_NAME + " WHERE idprodotto = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}
	
	@Override
	public synchronized boolean doModify(ItemBean bean) throws SQLException {
		Connection connection = null;
		Statement stmt = null;

		int result = 0;
		int updateEXP_done=0;
		//String modifySQL = "UPDATE `shoes`.`prodotto` SET nome= "+bean.getNome()+", descrizione= "+bean.getDescrizione()+", prezzo = "+bean.getPrezzo()+", img= "+bean.getImg()+", idcategoria= "+bean.getCategoria()+", quantita= "+bean.getQuantita()+" WHERE idprodotto= "+bean.getId_Prodotto();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			//stmt=connection.createStatement();
			//result = stmt.executeUpdate(modifySQL);
			PreparedStatement updateEXP = connection.prepareStatement("update `shoes`.`prodotto` set `nome` = ?,`descrizione` = ?,`prezzo` = ?,`img` = ?,`idcategoria` = ?,`quantita` = ? where `idprodotto` = ? ");
			updateEXP.setString(1, bean.getNome());
			updateEXP.setString(2, bean.getDescrizione());
			updateEXP.setDouble(3, bean.getPrezzo());
			updateEXP.setString(4, bean.getImg());
			updateEXP.setInt(5, bean.getCategoria());
			updateEXP.setInt(6, bean.getQuantita());
			updateEXP.setString(7, bean.getId_Prodotto());
			
			
			updateEXP_done = updateEXP.executeUpdate();


		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (updateEXP_done != 0);
	}

	@Override
	public synchronized Collection<ItemBean> doRetrieveAll(String category) throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs=null;

		Collection<ItemBean> products = new LinkedList<ItemBean>();

		String selectSQL = "SELECT * FROM " + ProductModelM.TABLE_NAME ;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			stmt=connection.createStatement();
			rs = stmt.executeQuery(selectSQL);

			while (rs.next()) {
				ItemBean bean = new ItemBean();

				bean.setId_Prodotto(rs.getString("idprodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setImg(rs.getString("img"));
				bean.setCategoria(rs.getInt("idcategoria"));
				bean.setQuantita(rs.getInt("quantita"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	@Override
	public synchronized ItemBean doRetrieveByName(String nomeProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ItemBean bean = new ItemBean();

		String selectSQL = "SELECT * FROM " + ProductModelM.TABLE_NAME + " WHERE nome = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nomeProdotto);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId_Prodotto(rs.getString("idprodotto"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setImg(rs.getString("img"));
				bean.setCategoria(rs.getInt("idcategoria"));
				bean.setQuantita(rs.getInt("quantita"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
}
