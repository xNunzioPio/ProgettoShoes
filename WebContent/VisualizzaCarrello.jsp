<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="CSS/cartStyle.css" rel="stylesheet" type="text/css">
<head>
<%@ page import="java.util.*" %>
	<%@ page import="model.ProductModelM" %>
	<%@ page import="model.ItemBean" %>
	<%@ page import="java.util.Collection" %>
	<%@ include file="navbar.jsp" %>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>
<%
session=request.getSession();
ArrayList<ItemBean> shoppingCar=(ArrayList)session.getAttribute("shoppingCar");
if(shoppingCar!=null)
{
%>
<h2 class="title">Carrello</h2>
	  <table>
		  <tr>
		    <th>&nbsp;Prodotto</th>
		    <th>Nome</th> 
		    <th>Prezzo</th>
		    <th>Taglia</th>
		    <th>Quantità</th>
	      </tr>
	  <% 
	  double som=0.0;
	  for(int i=0;i<shoppingCar.size();i++){%>
	  <div class="col-4" >
		  <tr>
		    <td><a href="ProdSelected.jsp?id=<%=shoppingCar.get(i).getId_Prodotto()%>" >
		                    <img src="<%=shoppingCar.get(i).getImg()%>"width="100" height="100">
		                </a><br><a class="remove" href="TogliDalCarrello?id=<%=shoppingCar.get(i).getId_Prodotto() %>&taglia=<%=shoppingCar.get(i).getTaglia()%>">Remove</a></td>
		    <td><%=shoppingCar.get(i).getNome() %></td> 
		    <td><%=shoppingCar.get(i).getPrezzo() %>0 €</td>
		    <td><%=shoppingCar.get(i).getTaglia() %></td>
		    <td class="last-td"><input class="qnt-cart" type="number" min="1" max="9" readonly value="<%=shoppingCar.get(i).getQuantita() %>"></td>
	      </tr>
      </div>
	  <%
	   som=(som+(shoppingCar.get(i).getPrezzo()*shoppingCar.get(i).getQuantita()));
	  }%>

</table>
<h4 class="tot">Totale:  <%=som%>0 €</h4>
<a href="Pagamento.jsp" ><button class="btn-conferma" >Compra</button></a>
<%}  
else {
%>
	<h2 class="title">Carrello</h2>
	<p class="cartVuoto"><strong>Nessun prodotto !</strong></p>
<%} %>
</body>
<%@ include file="footer.html" %>
</html>