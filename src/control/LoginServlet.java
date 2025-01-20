package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.CustomerBean;
import model.CustomerDM;
import model.UserBean;
import model.UserDM;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBean user=new UserBean();
	private CustomerBean cliente=new CustomerBean();
	static UserDM usr=new UserDM();
	static CustomerDM cst=new CustomerDM();

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		user.setEmail(email);
		user.setPassword(pass);
		int trovato=-1;
		
		try {
			UserBean userCercato=new UserBean();
			//CONTROLLIAMO SE L'UTENTE ï¿½ PRESENTE NEL DB
			//SE PRESENTE, RITORNA UN UserBean CON IDUTENTE/ROLE/EMAIL/PASSWORD
			//ALTRIMENTI RITORNA UN UserBean CON IDUTENTE=-1
			userCercato=usr.searchUser(user);
			//SE PRESENTE, USIAMO L'ID OTTENUTO PER OTTENERE TUTTI I DATI DEL CLIENTE
			//E AGGIUNGERLI ALLA SESSIONE
			if(userCercato.getIdUser()!=-1) {
				cliente=cst.doRetrieveByKey(userCercato.getIdUser());
				HttpSession session=request.getSession();
				session.setAttribute("id", userCercato.getIdUser());
				session.setAttribute("email", userCercato.getEmail());
				session.setAttribute("roleUser", userCercato.getRole());
				session.setAttribute("nome",cliente.getNome());
				session.setAttribute("Cognome",cliente.getCognome());
				session.setAttribute("dataNascita", cliente.getDataNascita());
				session.setAttribute("citta", cliente.getCitta());
				session.setAttribute("cap", cliente.getCAP());
				session.setAttribute("indirizzo", cliente.getIndirizzo());
				session.setAttribute("telefono", cliente.getTelefono());
				trovato=1;
			}
			else {
	            request.getRequestDispatcher("index.html").include(request, response);		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(trovato==-1)
		response.sendRedirect("index.html");
		else
			response.sendRedirect("home.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
