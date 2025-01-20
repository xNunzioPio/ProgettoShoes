package model;

public class SpedizioneBean {
private int idSpedizione;
private String date;
private String Indirizzo;
private String citta;
private int cap;
public SpedizioneBean() {
	
}
public String getIndirizzo() {
	return Indirizzo;
}
public void setIndirizzo(String indirizzo) {
	Indirizzo = indirizzo;
}
public int getIdSpedizione() {
	return idSpedizione;
}
public void setIdSpedizione(int idSpedizione) {
	this.idSpedizione = idSpedizione;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getCap() {
	return cap;
}
public void setCap(int cap) {
	this.cap = cap;
}
public String getCitta() {
	return citta;
}
public void setCitta(String citta) {
	this.citta = citta;
}
}
