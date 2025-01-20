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
 * Servlet implementation class InserisciProdotto
 */
@WebServlet("/InserisciProdotto")
public class InserisciProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InserisciProdotto() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome=request.getParameter("nome");
		String descrizione=request.getParameter("descrizione");
		String s_prezzo=request.getParameter("prezzo");
		String img=request.getParameter("img");
		String s_categoria=request.getParameter("categoria");
		String s_quantita=request.getParameter("quantita");
		
		double prezzo=Double.parseDouble(s_prezzo);
		int categoria=Integer.parseInt(s_categoria);
		int quantita=Integer.parseInt(s_quantita);
		
		ItemBean prodotto= new ItemBean();
		prodotto.setCategoria(categoria);
		prodotto.setDescrizione(descrizione);
		prodotto.setImg(img);
		prodotto.setNome(nome);
		prodotto.setPrezzo(prezzo);
		prodotto.setQuantita(quantita);
		
		ProductModelM pm=new ProductModelM();
		try {
			pm.doSave(prodotto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("AdminPage.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
