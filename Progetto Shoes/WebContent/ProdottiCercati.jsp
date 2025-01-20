<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.ProductModelM" %>
<%@ page import="model.ItemBean" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html; charset=UTF-8"  %>


<!DOCTYPE html>
<html>
<head>
    <%@ include file = "navbar.jsp"%>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/styleHome.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.3/css/fontawesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" >
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <title>Shoes&amp;Shoes</title>
</head>
<body>
	<h2 class="title">Prodotti cercati</h2>

<%
ArrayList<String> ids=new ArrayList<String>();
ProductModelM prodModel= new ProductModelM();
Collection<?> prodotti = prodModel.doRetrieveAll("idprodotto");
ArrayList<ItemBean> allProd=new ArrayList<ItemBean>();
ArrayList<ItemBean> prodTrovati=new ArrayList<ItemBean>();
Iterator<?> it = prodotti.iterator();
String text=request.getParameter("text");
text=text.toLowerCase();
while (it.hasNext()) {
	ItemBean bean = (ItemBean) it.next();
	if(bean.getCategoria()!=0)
		allProd.add(bean);
}
for(int c=0;c<allProd.size();c++){
	String compare=allProd.get(c).getNome().toLowerCase();
	if(compare.contains(text)){
		prodTrovati.add(allProd.get(c));
	}	
}
int i=0;
int j=prodTrovati.size();
%>

<div class="small-container">
        <div class="row">
        
        <%if(prodTrovati.size() > 0 ){ %>
				<% for(i=0;i<j;i++){ %>
				
				 			<%if(i>=j)break; %>
				            <div class="col-4">
				                <a href="ProdSelected.jsp?id=<%=prodTrovati.get(i).getId_Prodotto()%>" >
				                    <img src="<%=prodTrovati.get(i).getImg()%>">
				                </a>
				                    <h4><%=prodTrovati.get(i).getNome() %></h4><br>
				                    <p><%=prodTrovati.get(i).getPrezzo() %>0 €</p>
				            </div>
				            <% i++; if(i>=j)break;%>
				            <div class="col-4">
				                <a href="ProdSelected.jsp?id=<%=prodTrovati.get(i).getId_Prodotto()%>" >
				                    <img src="<%=prodTrovati.get(i).getImg()%>">
				                </a>
				                    <h4><%=prodTrovati.get(i).getNome() %></h4><br>
				                    <p><%=prodTrovati.get(i).getPrezzo() %>0 €</p>
				            </div>
				            <% i++; if(i>=j)break;%>
				            <div class="col-4">
				                <a href="ProdSelected.jsp?id=<%=prodTrovati.get(i).getId_Prodotto()%>" >
				                    <img src="<%=prodTrovati.get(i).getImg()%>">
				                </a>
				                <h4><%=prodTrovati.get(i).getNome() %></h4><br>
				                <p><%=prodTrovati.get(i).getPrezzo() %>0 €</p>  
				            </div>
				            <% i++; if(i>=j)break; %>
				            <div class="col-4">
				                <a href="ProdSelected.jsp?id=<%=prodTrovati.get(i).getId_Prodotto()%>" >
				                    <img src="<%=prodTrovati.get(i).getImg()%>">
				                </a>
				                <h4><%=prodTrovati.get(i).getNome() %></h4><br>
				                <p><%=prodTrovati.get(i).getPrezzo() %>0 €</p>  
				            </div>
				<%} %>
		<%} else {%>
							<p class="risRicerca"><strong>Nessun Risultato !</strong></p>
		<%} %>
        </div>
</div>
<%@ include file = "footer.html"%>
    
</body>
</html>