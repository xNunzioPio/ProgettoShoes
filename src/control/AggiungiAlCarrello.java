package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemBean;
import model.OrdineBean;
import model.ProductModelM;


@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AggiungiAlCarrello() {
        super();
    }
//Servlet che aggiunge un oggetto al carrello prendendo l'id 
//come parametro dalla richiesta della prodselected.jsp 
//e aggiunge alla sessione il carrello

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	       ServletContext application=getServletContext() ;
		   ServletConfig config=getServletConfig() ;
		   response.setContentType("text/html");
		   PrintWriter out=response.getWriter(); 
		   HttpSession session =request.getSession();
		   String id=(String) session.getAttribute("idProdotto");
		   String num=request.getParameter("qnt-Prodotto");
		   String taglia=request.getParameter("x-taglia");
		   int t=-1,q=-1;
		   t=Integer.parseInt(taglia);
		   q=Integer.parseInt(num);
		   
		 if(id!=null && t!=-1 && q!=-1) {
		 ArrayList<ItemBean> shoppingCar= (ArrayList) session.getAttribute("shoppingCar");
		 if(shoppingCar==null) {
			 shoppingCar=new ArrayList<ItemBean>();
			 session.setAttribute("numProd", "1");
		 }
		 else {
			 String numProd=(String) session.getAttribute("numProd");
			 int x=Integer.parseInt(numProd);
			 x=x+1;
			 String y=""+x;
			 session.setAttribute("numProd",y);
		 }
		 ProductModelM prodModel= new ProductModelM();
		 Collection<?> prodotti;
		 try {
			prodotti = prodModel.doRetrieveAll("idprodotto");
			 Iterator<?> it = prodotti.iterator();
			 while (it.hasNext()) {
			 	ItemBean bean = (ItemBean) it.next();
			 	if(bean.getId_Prodotto().equals(id)) {
			 		if(shoppingCar.size()==0) {
			 			bean.setTaglia(t);
				 	    bean.setQuantita(q);
				        shoppingCar.add(bean);
			 		}
			 		else {
			 			bean.setTaglia(t);
			 			String taglia1=""+bean.getTaglia();
			 			String taglia2;
			 			int flag=0;
			 			for(int i=0;i<shoppingCar.size();i++) {
			 				taglia2=""+shoppingCar.get(i).getTaglia();
			 				if(bean.getId_Prodotto().equals(shoppingCar.get(i).getId_Prodotto()) && taglia1.equals(taglia2)) {
			 					int x=shoppingCar.get(i).getQuantita()+1;
			 					shoppingCar.get(i).setQuantita(x);
			 					flag++;
			 					break;
			 				}
			 			}
			 			if(flag==0) {
			 				bean.setTaglia(t);
					 	    bean.setQuantita(q);
					        shoppingCar.add(bean);
			 			}
			 		}
			   }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		   session.setAttribute("shoppingCar",shoppingCar); 
		  }
		  response.sendRedirect("ProdSelected.jsp?id="+id); 
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
