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


@WebServlet("/ModificaProdotto")
public class ModificaProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModificaProdotto() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("idProdotto");
		String nome=request.getParameter("nome2");
		String descrizione=request.getParameter("descrizione2");
		String prezzo=request.getParameter("prezzo2");
		String img=request.getParameter("img2");
		String cat=request.getParameter("categoria2");
		String quant=request.getParameter("quantita2");
		//CREAZIONE ITEMBEAN
		ItemBean prodotto=new ItemBean();
		prodotto.setId_Prodotto(id);
		prodotto.setNome(nome);
		prodotto.setDescrizione(descrizione);
		double p=Double.parseDouble(prezzo);
		int c=Integer.parseInt(cat);
		int q=Integer.parseInt(quant);
		prodotto.setPrezzo(p);
		prodotto.setImg(img);
		prodotto.setCategoria(c);
		prodotto.setQuantita(q);
		//CREAZIONE PRODUCTMODELM
		ProductModelM pm=new ProductModelM();
		try {
			boolean verifica=false;
			verifica=pm.doModify(prodotto);
			if(!verifica) {
				System.out.println("Modifica non effettuata!");
			}
			else {
				System.out.println("Modifica effettuata");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("AdminPage.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
