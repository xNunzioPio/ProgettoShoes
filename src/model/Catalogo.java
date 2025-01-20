package model;

import java.util.ArrayList;

public class Catalogo {
	
private	static ArrayList<ItemBean> prodotti;
	
public Catalogo() {
		prodotti= new ArrayList<ItemBean>();
	}


public static ItemBean getItem(String id) {
	for(int i=0;i<prodotti.size();i++) {
		if(prodotti.get(i).getId_Prodotto().equals(id))
			return prodotti.get(i);
	}
	return null;
}
public static ItemBean getItem(int i) {
	return prodotti.get(i);
}
public  String getId(int i) {
	return prodotti.get(i).getId_Prodotto();
}
public ArrayList<ItemBean> getCatalogo() {
	return prodotti;
}
public void addItem(ItemBean item) {
	prodotti.add(item);
}
}
