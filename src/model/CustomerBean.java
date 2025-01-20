package model;

import java.io.Serializable;

public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int idCliente;
	String nome;
	String cognome;
	String data_nascita;
	String citta;
	int cap;
	String indirizzo;
	String tel;
	
	public CustomerBean() {
		idCliente=-1;
		nome="";
		cognome="";
		data_nascita="";
		citta="";
		cap=0;
		indirizzo="";
		tel="";
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int id) {
		idCliente=id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String n) {
		nome=n;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String c) {
		cognome=c;
	}
	public String getDataNascita() {
		return data_nascita;
	}
	public void setDataNascita(String data) {
		data_nascita=data;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String cit) {
		citta=cit;
	}
	public int getCAP() {
		return cap;
	}
	public void setCAP(int c) {
		cap=c;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String ind) {
		indirizzo=ind;
	}
	public String getTelefono() {
		return tel;
	}
	public void setTelefono(String telefono) {
		tel=telefono;
	}
	
}
