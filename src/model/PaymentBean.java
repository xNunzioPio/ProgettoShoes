package model;

public class PaymentBean {
	private int idpagamento;
	private String date;
	private double importo;
	private double iva;
public PaymentBean() {
	
}
public int getIdpagamento() {
	return idpagamento;
}
public void setIdpagamento(int idpagamento) {
	this.idpagamento = idpagamento;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public double getImporto() {
	return importo;
}
public void setImporto(double importo) {
	this.importo = importo;
}
public double getIva() {
	return iva;
}
public void setIva(double iva) {
	this.iva = iva;
}
}
