package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductModelM;

public class ProductControl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static model.ProductModel model=new ProductModelM();
	public ProductControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
        String par =request.getParameter("param");
        if(par.equals("uomo")) {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Uomo.jsp");
		dispatcher.forward(request, response);
        }
        if(par.equals("donna")) {
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Donna.jsp");
    		dispatcher.forward(request, response);
            }
        if(par.equals("bambino")) {
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Bambino.jsp");
    		dispatcher.forward(request, response);
            }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
