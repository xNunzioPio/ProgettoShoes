package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class OrdineBean {
	private int idCliente;
	private int idOrdine;
	private int idPagamento;
	private int idSpedizione;
	private String data;
	private int iva;
	public OrdineBean() {
		
	}
	
	//METODI
	public void setIdCliente(int id) {
		idCliente=id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdOrdine(int id) {
		idOrdine=id;
	}
	public void setIdPagamento(int id) {
		idPagamento=id;
	}
	public void setIdSpedizione(int id) {
		idSpedizione=id;
	}
	public void setData(String dat) {
		data=dat;
	}
	public void setIva(int i) {
		iva=i;
	}
	public int getIdOrdine() {
		return idOrdine;
	}
	public int getIdPagamento() {
		return idPagamento;
	}
	public int getIdSpedizione() {
		return idSpedizione;
	}
	public String getData() {
		return data;
	}
	public int getIva() {
		return iva;
	}
}
