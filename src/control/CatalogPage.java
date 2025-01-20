package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.Catalogo;

//LA SERVLET CHE ESTENDE CatalogPage, DEVE CHIAMARE I METODI
//setProdotti e setTitolo nell'istanziazione prima che ci sia l'accesso alla servlet


//DA CAPIRE SE SERVE
//DA CAPIRE SE SERVE
//DA CAPIRE SE SERVE
//DA CAPIRE SE SERVE
//DA CAPIRE SE SERVE
//DA CAPIRE SE SERVE








@WebServlet("/CatalogPage")
public abstract class CatalogPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemBean[] prodotti;
	private String[] idProdotti;
	private String titolo;

	protected void setProdotti(String[] idProdotti) {
		this.idProdotti=idProdotti;
		prodotti=new ItemBean[idProdotti.length];
		for(int i=0;i<prodotti.length;i++) {
			prodotti[i]=Catalogo.getItem(idProdotti[i]);
		}
	}
	
	protected void setTitolo(String titolo) {
		this.titolo=titolo;
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
