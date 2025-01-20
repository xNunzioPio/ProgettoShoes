package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.OrdineProdotto;
import model.ShoppingCart;

/* Mostra tutti gli articoli attualmente nel carrello. Clienti
hanno la propria sessione che tiene traccia del proprio carello.
Se questa è la loro prima visita alla pagina dell'ordine,
viene creato un nuovo carrello.
Di solito, le persone arrivano a questa pagina tramite una pagina che
mostra le voci del catalogo, quindi questa pagina aggiunge un'ulteriore
articolo nel carrello. Ma gli utenti possono anche
aggiungere questa pagina ai segnalibri, accedervi dall'elenco della cronologia,
o essere rispedito ad esso facendo clic sul pulsante "Aggiorna ordine"
dopo aver modificato il numero di articoli ordinati.
 */


@WebServlet("/ServletPaginaOrdine")
public class ServletPaginaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletPaginaOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	    ShoppingCart cart;
	    synchronized(session) {
	        cart = (ShoppingCart)session.getAttribute("shoppingCart");
	        // New visitors get a fresh shopping cart.
	        // Previous visitors keep using their existing cart.
	        if (cart == null) {
	          cart = new ShoppingCart();
	          session.setAttribute("shoppingCart", cart);
	        }
	        String itemID = request.getParameter("itemID");
	        if (itemID != null) {
	          String numItemsString = request.getParameter("numItems");
	          if (numItemsString == null) {
	            // If request specified an ID but no number,
	            // then customers came here via an "Add Item to Cart"
	            // button on a catalog page.
	            cart.addItem(itemID);
	          } else {
	            // If request specified an ID and number, then
	            // customers came here via an "Update Order" button
	            // after changing the number of items in order.
	            // Note that specifying a number of 0 results
	            // in item being deleted from cart.
	            int numItems;
	            try {
	              numItems = Integer.parseInt(numItemsString);
	            } catch(NumberFormatException nfe) {
	              numItems = 1;
	            }
	            cart.setNumOrdered(itemID, numItems);
	          }
	        }
	      }
	      // Whether or not the customer changed the order, show order status.
	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Stato del tuo Ordine";
	      String docType =
	        "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	        "Transitional//EN\">\n";
	      out.println(docType +
	                  "<HTML>\n" +
	                  "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
	                  "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                  "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
	      
	      synchronized(session) {
	        List itemsOrdered = cart.getItemsOrdered();
	        if (itemsOrdered.size() == 0) {
	          out.println("<H2><I>Non ci sono Prodotti nel tuo Carello...</I></H2>");
	        } else {
	          // If there is at least one item in cart, show table of items ordered.
	          out.println
	            ("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
	             "<TR BGCOLOR=\"#FFAD00\">\n" +
	             "  <TH>ID Prodotto<TH>Descrizione\n" +
	             "  <TH>Costo per Unità<TH>Quantità<TH>Costo Totale");
	          OrdineProdotto order;
	          // Rounds to two decimal places, inserts dollar
	          // sign (or other currency symbol), etc., as
	          // appropriate in current Locale.
	          NumberFormat formatter = NumberFormat.getCurrencyInstance();
	          // For each entry in shopping cart, make
	          // table row showing ID, description, per-item
	          // cost, number ordered, and total cost.
	          // Put number ordered in textfield that user
	          // can change, with "Update Order" button next
	          // to it, which resubmits to this same page
	          // but specifying a different number of items.
	          for(int i=0; i<itemsOrdered.size(); i++) {
	            order = (OrdineProdotto)itemsOrdered.get(i);
	            out.println
	              ("<TR>\n" +
	               "  <TD>" + order.getItemID() + "\n" +
	               "  <TD>" + order.getDescription() + "\n" +
	               "  <TD>" +
	               formatter.format(order.getUnitCost()) + "\n" +
	               "  <TD>" +
	               "<FORM>\n" +  // Submit to current URL
	               "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"\n" +
	               "       VALUE=\"" + order.getItemID() + "\">\n" +
	               "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
	               "       SIZE=3 VALUE=\"" + 
	               order.getNumItems() + "\">\n" +
	               "<SMALL>\n" +
	               "<INPUT TYPE=\"SUBMIT\"\n "+
	               "       VALUE=\"Update Order\">\n" +
	               "</SMALL>\n" +
	               "</FORM>\n" +
	               "  <TD>" +
	               formatter.format(order.getTotalCost()));
	          }
	          String checkoutURL = response.encodeURL("Checkout.html");
	          // "Proceed to Checkout" button below table
	          out.println
	            ("</TABLE>\n" +
	             "<FORM ACTION=\"" + checkoutURL + "\">\n" +
	             "<BIG><CENTER>\n" +
	             "<INPUT TYPE=\"SUBMIT\"\n" +
	             "       VALUE=\"Proceed to Checkout\">\n" +
	             "</CENTER></BIG></FORM>");
	        }
	        out.println("</BODY></HTML>");
	      }
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
