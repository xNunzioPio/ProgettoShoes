package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerDM;
import model.OrdineBean;
import model.OrdineDM;


@WebServlet("/CercaOrdine")
public class CercaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CercaOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nomeCliente");
		String cognome=request.getParameter("cognomeCliente");
		String data_DAL=request.getParameter("data1");
		String data_AL=request.getParameter("data2");
		CustomerBean cliente=new CustomerBean();
		cliente.setCognome(cognome);
		cliente.setNome(nome);
		CustomerDM dm =new CustomerDM();
		ArrayList<Integer> idClienti=new ArrayList<Integer>();
		try {
			idClienti=dm.doRetrieveByName(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		OrdineDM ordini = new OrdineDM();
		ArrayList<OrdineBean> ordiniTrovati=new ArrayList<OrdineBean>();
		try {
			if(idClienti.size()>0) {
				for(int i=0;i<idClienti.size();i++) {
					ArrayList<OrdineBean> ordiniParziali=new ArrayList<OrdineBean>();
					ordiniParziali=ordini.doRetrieveByidCliente(idClienti.get(i),data_DAL,data_AL);
					for(int k=0;k<ordiniParziali.size();k++) {
						OrdineBean ordine=new OrdineBean();
						ordine=(OrdineBean) ordiniParziali.get(k);
						ordiniTrovati.add(ordine);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html");
		HttpSession session =request.getSession();
		session.removeAttribute("ordiniTrovati");
		session.setAttribute("ordiniTrovati", ordiniTrovati);
		response.sendRedirect("AdminPage.jsp"); 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
