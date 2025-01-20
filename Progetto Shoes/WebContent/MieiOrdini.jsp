<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="navbar.jsp" %>
<%@ page import="model.OrdineDM" %>
<%@ page import="model.OrdineBean" %>
<%@ page import="model.Composizione" %>
<%@ page import="model.ComposizioneDM" %>
	<link href="CSS/cartStyle.css" rel="stylesheet" type="text/css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>I Miei Ordini</title>
</head>

<body>
	<h2 class="title">I Miei Ordini</h2>
	<%	session=request.getSession();
		if(session.getAttribute("id")==null){%>
	<p class="cartVuoto"><strong>Nessun ordine !</strong></p>	
	<%	}
		else {
			String idUser=""+(int)session.getAttribute("id");
			System.out.println("ID utente : "+idUser);
			OrdineDM searchOrder = new OrdineDM();
			ArrayList<OrdineBean> ordini=new ArrayList<OrdineBean>();
			ordini=searchOrder.doRetrieveByidCliente2(Integer.parseInt(idUser));
			if(ordini==null){%>
	<p class="cartVuoto"><strong>Nessun ordine !</strong></p>
			<%} 
	
			else{	
				ComposizioneDM compDM=new ComposizioneDM();
				ArrayList<Composizione> prodOrdinati=new ArrayList<Composizione>();
				ArrayList<ItemBean> prodTotali=new ArrayList<ItemBean>();
				Composizione compOrdine=new Composizione();
				for(int i=0;i<ordini.size();i++){
					compOrdine.setIdOrdine(ordini.get(i).getIdOrdine());
					prodOrdinati=compDM.doRetrieveByidOrdine(compOrdine);
					for(int j=0;j<prodOrdinati.size();j++){
						ItemBean prodotto=new ItemBean();
						prodotto.setId_Prodotto(""+prodOrdinati.get(j).getIdProdotto());
						prodotto.setQuantita(prodOrdinati.get(j).getQuantita());
						prodTotali.add(prodotto);
					}
				}					
				ProductModelM prodModel= new ProductModelM();
				Collection<?> prodotti = prodModel.doRetrieveAll("idprodotto");
				ArrayList<ItemBean> allProducts=new ArrayList<ItemBean>();
				Iterator<?> it = prodotti.iterator();
				while (it.hasNext()) {
					ItemBean bean = (ItemBean) it.next();
					if(bean.getCategoria()!=0)
						allProducts.add(bean);
				}
				for(int k=0;k<prodTotali.size();k++){
					for(int n=0;n<allProducts.size();n++){
						if(prodTotali.get(k).getId_Prodotto().equals(allProducts.get(n).getId_Prodotto())){
							prodTotali.get(k).setNome(allProducts.get(n).getNome());
							prodTotali.get(k).setPrezzo(allProducts.get(n).getPrezzo());
							prodTotali.get(k).setImg(allProducts.get(n).getImg());
						}
					}
				}%>
				<table>
				  <tr>
				    <th>&nbsp;Prodotto</th>
				    <th>Nome</th> 
				    <th>Prezzo</th>
				    <th>Quantità</th>
			      </tr>
			      <%for(int i=0;i<prodTotali.size();i++) {%>
				  
				  <tr>
				    <td><a href="ProdSelected.jsp?id=<%=prodTotali.get(i).getId_Prodotto()%>" >
				                    <img src="<%=prodTotali.get(i).getImg()%>"width="100" height="100">
				                </a></td>
				    <td><%=prodTotali.get(i).getNome() %></td> 
				    <td><%=prodTotali.get(i).getPrezzo() %>0 €</td>
				    <td class="last-td"><input class="qnt-cart" type="number" readonly value="<%=prodTotali.get(i).getQuantita() %>"></td>
	      		  </tr>
			
			
					<%}%>
			    </table>	
			<%}
		}
	%>
	
	
</body>

<%@ include file="footer.html" %>
</html>