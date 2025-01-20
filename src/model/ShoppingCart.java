package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private ArrayList prodottiOrdinati;
	
	
	public ShoppingCart() {
		prodottiOrdinati=new ArrayList();
	}
	
	
	//METODI
	public List getItemsOrdered() {
		return prodottiOrdinati;
	}
	
	public synchronized void addItem(String itemID) {
	    OrdineProdotto order;
	    for(int i=0; i<prodottiOrdinati.size(); i++) {
	      order = (OrdineProdotto)prodottiOrdinati.get(i);
	      if (order.getItemID().equals(itemID)) {
	        order.incrementNumItems();
	        return;
	      }
	    }
	    OrdineProdotto newOrder = new OrdineProdotto(Catalogo.getItem(itemID));
	    prodottiOrdinati.add(newOrder);
	  }
	
	public synchronized void setNumOrdered(String itemID,int numOrdered) {

		OrdineProdotto order;
		for(int i=0; i<prodottiOrdinati.size(); i++) {
			order = (OrdineProdotto)prodottiOrdinati.get(i);
			if (order.getItemID().equals(itemID)) {
				if (numOrdered <= 0) {
					prodottiOrdinati.remove(i);
				} else {
					order.setNumItems(numOrdered);
				}
				return;
			}
		}
		OrdineProdotto newOrder = new OrdineProdotto(Catalogo.getItem(itemID));
		prodottiOrdinati.add(newOrder);
	}

}
