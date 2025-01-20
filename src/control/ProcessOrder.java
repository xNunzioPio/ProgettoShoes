package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ComposizioneDM;
import model.ItemBean;
import model.OrdineBean;
import model.OrdineDM;
import model.PaymentBean;
import model.PaymentDM;
import model.SpedizioneBean;
import model.SpedizioneDM;

/**
 * Servlet implementation class ProcessOrder
 */
@WebServlet("/ProcessOrder")
public class ProcessOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
//Servlet che processa l'ordine dopo aver inserito
//i dati della carta di credito
//Prende il carrello e i dati dell'utente per registrare
//l'ordine nel db
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		//Verifico se un utente è loggato altrimenti lo fa loggare/registrare
		if(session.getAttribute("id")==null) {
			response.sendRedirect("Profile.jsp");
		}else {
			try {
				PaymentDM pagamento= new PaymentDM();
			    OrdineDM ordine= new OrdineDM();
			    SpedizioneDM spedizione= new SpedizioneDM();
			    ComposizioneDM composizione= new ComposizioneDM();
			    PaymentBean p=new PaymentBean();
			    OrdineBean o=new OrdineBean();
			    SpedizioneBean s=new SpedizioneBean();
			    
			    int idConteggio=ordine.lastID()+1;
			    
			    String indirizzo= (String)session.getAttribute("indirizzo");
			    String citta= (String)session.getAttribute("citta");
			    int cap= (int)session.getAttribute("cap");
			    int id=(int) session.getAttribute("id");
			    
				//Prendo il carrello dalla sessione
				ArrayList<ItemBean> shoppingCar=(ArrayList<ItemBean>) session.getAttribute("shoppingCar");
				String todaysDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			    //Ordine
				o.setIdCliente(id);
			    o.setData(todaysDate);
			    o.setIdPagamento(idConteggio);
			    o.setIva(22);
			    o.setIdSpedizione(idConteggio);
			    ordine.doSave(o);
			    int nOrdine=ordine.lastID();
			    double som=0;
			  	for(int i=0;i<shoppingCar.size();i++) {
				    	composizione.doSave(nOrdine,Integer.parseInt(shoppingCar.get(i).getId_Prodotto()),shoppingCar.get(i).getQuantita());
				    	som=som+(shoppingCar.get(i).getPrezzo()*shoppingCar.get(i).getQuantita());
			    }
			    //Pagamento
			  	double iva=22.00;
			    p.setIdpagamento(idConteggio);
			    p.setDate(todaysDate);
			    p.setImporto(som);
			    p.setIva(iva);
			    pagamento.doSave(p);
			    //Spedizione
			    s.setIdSpedizione(idConteggio);
			    s.setDate(todaysDate);
			    s.setIndirizzo(indirizzo);
			    s.setCitta(citta);
			    s.setCap(cap);
			    spedizione.doSave(s);
			    //UNA VOLTA FATTO TUTTO SVUOTIAMO IL CARRELLO
			    ArrayList<ItemBean> cartVuoto=new ArrayList<ItemBean>();
			    session.setAttribute("shoppingCar",cartVuoto);
			    session.setAttribute("numProd","0");
			    response.sendRedirect("home.jsp");
			}
			catch(SQLException e) {
			}
		}
}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
