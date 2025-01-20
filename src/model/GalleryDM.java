package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

public final class GalleryDM {
	
	private static final String TABLE_NAME = "galleria";
	
public synchronized void doSave(GalleryBean galleryBean) throws SQLException{
		
		Connection con=null;
		PreparedStatement preparedStatement = null;;
		

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (idprodotto, img1, img2, img3, img4) VALUES (?, ?, ?, ?, ?)";
	
		try {
			con=DriverManagerConnectionPool.getConnection();
			preparedStatement = con.prepareStatement(insertSQL);
			preparedStatement.setInt(1, galleryBean.getId());
			preparedStatement.setString(2, galleryBean.getImg1());
			preparedStatement.setString(3, galleryBean.getImg2());
			preparedStatement.setString(4, galleryBean.getImg3());
			preparedStatement.setString(5, galleryBean.getImg4());
			
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
	
public synchronized GalleryBean doRetrieveByid(int id) throws SQLException{
	Connection con=null;
	Statement stmt=null;
	
	GalleryBean imgProd=new GalleryBean();
	
	String selectSQL="SELECT * FROM shoes.galleria where idprodotto="+id;
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			imgProd.setId(rs.getInt("idprodotto"));
			imgProd.setImg1(rs.getString("img1"));
			imgProd.setImg2(rs.getString("img2"));
			imgProd.setImg3(rs.getString("img3"));
			imgProd.setImg4(rs.getString("img4"));
		}
	} finally {
		try {
			if(stmt != null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return imgProd;
	
}

public synchronized boolean doDelete(int id) throws SQLException {
	Connection con=null;
	Statement stmt=null;
	
	int result=0;
	
	String deleteSQL="DELETE FROM `shoes`.`galleria` WHERE (`idprodotto` = '"+id+"')";
	
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

public synchronized Collection<GalleryBean> doRetrieveAll() throws SQLException {
	Connection con=null;
	Statement stmt=null;
	
	Collection<GalleryBean>imgProdotti=new LinkedList<GalleryBean>();
	
	String selectSQL="SELECT * FROM shoes.galleria";
	
	try {
		con=DriverManagerConnectionPool.getConnection();
		stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery(selectSQL);
		
		while(rs.next()) {
			GalleryBean imgProdotto=new GalleryBean();
			imgProdotto.setId(rs.getInt("idprodotto"));
			imgProdotto.setImg1(rs.getString("img1"));
			imgProdotto.setImg2(rs.getString("img2"));
			imgProdotto.setImg3(rs.getString("img3"));
			imgProdotto.setImg4(rs.getString("img4"));
			imgProdotti.add(imgProdotto);
		}
	} finally {
		try {
			if(stmt!=null)
				stmt.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(con);
		}
	}
	return imgProdotti;	
}


}
