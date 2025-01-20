package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerBean;
import model.CustomerDM;
import model.UserBean;
import model.UserDM;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private int idBean=0;
	private static final long serialVersionUID = -7690848414002443577L;
	private ArrayList<Integer> allIDs=new ArrayList<Integer>();
	private UserBean utente=new UserBean();
	private CustomerBean cliente=new CustomerBean();
	static UserDM usr=new UserDM();
	static CustomerDM cst=new CustomerDM();
	
	
	
    public Registration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome=request.getParameter("nome");
		String cognome=request.getParameter("cognome");
		String data=request.getParameter("data_nascita");
		String cit=request.getParameter("citta");
		String capString=request.getParameter("cap");
		String ind=request.getParameter("indirizzo");
		String tel=request.getParameter("telefono");
		String email=request.getParameter("email");
		String pass=request.getParameter("psw");
		//CONVERTO LA STRINGA IN INT
		int cap=Integer.parseInt(capString);
		try {
			allIDs=cst.allID();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if(allIDs !=null) {
			idBean=allIDs.get(allIDs.size()-1);
			idBean=idBean+1;
			System.out.println(idBean);
		}
		
		
		//MEMORIZZIAMO I DATI OTTENUTI DAL FORM NEL BEAN DEL CLIENTE
		cliente.setIdCliente(idBean);
		cliente.setNome(nome);
		cliente.setCognome(cognome);
		cliente.setDataNascita(data);
		cliente.setCitta(cit);
		cliente.setCAP(cap);
		cliente.setIndirizzo(ind);
		cliente.setTelefono(tel);
		//SETTIAMO ANCHE IL BEAN UTENTE
		utente.setIdUser(idBean);
		utente.setRole("user");
		utente.setEmail(email);
		utente.setPassword(pass);
		
		try {
			//SALVIAMO IL CUSTOMERBEAN NEL DB
			cst.doSave(cliente);
			//SALVIAMO L'USERBEAN NEL DB
			usr.doSave(utente);
			
		}
		catch ( SQLException e) {
		System.out.println("Error:" + e.getMessage());
		}
		response.sendRedirect("index.html");	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
