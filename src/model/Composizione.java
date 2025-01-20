package model;

public class Composizione {
	
	private int idOrdine;
	private int idProdotto;
	private int quantita;
	
	public Composizione() {
		
	}
	
	public void setIdOrdine(int id) {
		idOrdine=id;
	}
	public void setIdProdotto(int id) {
		idProdotto=id;
	}
	public void setQuantita(int q) {
		quantita=q;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public int getQuantita() {
		return quantita;
	}

}
