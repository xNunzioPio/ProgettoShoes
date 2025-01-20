package model;

public class ItemBean {
	private String id_Prodotto;
	private String nome;
	private String descrizione;
	private double prezzo;
	private String img;
	private int quantita;
	private int categoria;
	private int taglia;


public ItemBean() {}


public String getId_Prodotto() {
	return id_Prodotto;
}
public void setId_Prodotto(String id_Prodotto) {
	this.id_Prodotto = id_Prodotto;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getDescrizione() {
	return descrizione;
}
public void setDescrizione(String descrizione) {
	this.descrizione = descrizione;
}
public double getPrezzo() {
	return prezzo;
}
public void setPrezzo(double prezzo) {
	this.prezzo = prezzo;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public void setQuantita(int q) {
 quantita=q;
}
public int getQuantita() {
	return quantita;
}
public int getCategoria() {
	return categoria;
}
public void setCategoria(int categoria) {
	this.categoria = categoria;
}


public int getTaglia() {
	return taglia;
}


public void setTaglia(int taglia) {
	this.taglia = taglia;
}
}
