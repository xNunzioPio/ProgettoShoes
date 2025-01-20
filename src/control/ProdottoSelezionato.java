package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ItemBean;
import model.ProductModelM;

@WebServlet("/ProdottoSelezionato")
public class ProdottoSelezionato  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static ProductModelM prod=new ProductModelM();
	static ItemBean trovato=new ItemBean();
	
	
	public ProdottoSelezionato() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");
		if(id == null)
			out.write(id);
		int idP=Integer.parseInt(id);
		
		try {
			if(  (trovato=prod.doRetrieveByKey(idP)) != null ) {
				request.setAttribute("id", id);
				out.write("Prodotto Trovato !");
				
			}
			else
				out.write("Prodotto non trovato !");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
