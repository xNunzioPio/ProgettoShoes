package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.ProductModelM;

/**
 * Servlet implementation class SalvaProdotto
 */
@WebServlet("/SalvaProdotto")
public class SalvaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public SalvaProdotto() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String descrizione=request.getParameter("descrizione");
		String prezzo=request.getParameter("prezzo");
		String img=request.getParameter("img");
		String cat=request.getParameter("categoria");
		String quant=request.getParameter("quantita");
		//CREAZIONE ITEMBEAN
		ItemBean prodotto=new ItemBean();
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		prodotto.setPrezzo(Integer.parseInt(prezzo));
		prodotto.setImg(img);
		prodotto.setCategoria(Integer.parseInt(cat));
		prodotto.setQuantita(Integer.parseInt(quant));
		//CREAZIONE PRODUCTMODELM
		ProductModelM pm=new ProductModelM();
		try {
			pm.doSave(prodotto);
		}
		catch( SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
