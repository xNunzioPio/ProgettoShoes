package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemBean;
import model.ProductModelM;


@WebServlet("/CercaProdotto")
public class CercaProdotto extends HttpServlet {
       
	private static final long serialVersionUID = 1L;


	public CercaProdotto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome=request.getParameter("nomeProdotto");
		ProductModelM pm=new ProductModelM();
		ItemBean prodotto=new ItemBean();
		try {
			prodotto=pm.doRetrieveByName(nome);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ServletContext application=getServletContext() ;
		ServletConfig config=getServletConfig() ;
		response.setContentType("text/html");
		HttpSession session =request.getSession();
		session.removeAttribute("prodottoTrovato");
		session.setAttribute("prodottoTrovato", prodotto);
		response.sendRedirect("AdminPage.jsp"); 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
