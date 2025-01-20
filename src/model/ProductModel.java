package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface ProductModel {
	public void doSave(ItemBean product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public boolean doModify(ItemBean bean) throws SQLException;

	public ItemBean doRetrieveByKey(int code) throws SQLException;
	
	public ItemBean doRetrieveByName(String nomeProdotto) throws SQLException;
	
	public ArrayList<ItemBean> doRetrieveByCategory(int category) throws SQLException;
	
	public Collection<ItemBean> doRetrieveAll(String order) throws SQLException;
}
