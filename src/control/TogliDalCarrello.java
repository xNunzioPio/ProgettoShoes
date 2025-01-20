package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemBean;


@WebServlet("/TogliDalCarrello")
public class TogliDalCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TogliDalCarrello() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 HttpSession session =request.getSession();
		 String id=request.getParameter("id");
		 String taglia=request.getParameter("taglia");
		 ArrayList<ItemBean> shoppingCar= (ArrayList) session.getAttribute("shoppingCar");
		 for(int i=0;i<shoppingCar.size();i++) {
			 String taglia2=""+shoppingCar.get(i).getTaglia();
			 if(taglia.equals(taglia2) && id.equals(shoppingCar.get(i).getId_Prodotto())) {
				 shoppingCar.remove(i);
				 break;
			 }
		 }
		 int size=0;
		 for(int i=0;i<shoppingCar.size();i++) {
			 size+=shoppingCar.get(i).getQuantita();
		 }
		 String numProd=""+size;
		 session.setAttribute("numProd", numProd);
		 session.setAttribute("shoppingCar", shoppingCar);
		 response.sendRedirect("VisualizzaCarrello.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
