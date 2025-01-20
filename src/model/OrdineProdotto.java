package model;

public class OrdineProdotto {
	private ItemBean prodotto;
	private int numProdotti;
	
	//COSTRUTTORE
	public OrdineProdotto(ItemBean prodot) {
		setItem(prodot);
		setNumItems(1);
	}
	
	//METODI
	public ItemBean getItem() {
		return prodotto;
	}
	protected void setItem(ItemBean prodotto) {
		this.prodotto=prodotto;
	}
	public String getItemID() {
		return (getItem().getId_Prodotto());
	}
	public String getDescription() {
		return (getItem().getDescrizione());
	}
	public double getUnitCost() {
		return (getItem().getPrezzo());
	}
	public int getNumItems() {
		return numProdotti;
	}
	public void setNumItems(int n) {
		this.numProdotti=n;
	}
	public void incrementNumItems() {
		setNumItems(getNumItems()+1);
	}
	public void cancelOrder() {
		setNumItems(0);
	}
	public double getTotalCost() {
		return (getNumItems() * getUnitCost());
	}
	
}
