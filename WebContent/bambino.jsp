<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.ProductModelM" %>
<%@ page import="model.ItemBean" %>
<%@ page import="java.util.Collection" %>


<!DOCTYPE html>
<html>

<%@ page contentType="text/html; charset=UTF-8"  %>
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
    <title>Bambino</title>
</head>
<body>
	<h2 class="title">Scarpe Bambino</h2>

<%
 
ProductModelM prodModel= new ProductModelM();
Collection<?> prodotti = prodModel.doRetrieveAll("idprodotto");
ArrayList<ItemBean> prodotti_Bambino=new ArrayList<ItemBean>();
Iterator<?> it = prodotti.iterator();
while (it.hasNext()) {
	ItemBean bean = (ItemBean) it.next();
	if(bean.getCategoria()==3)
	prodotti_Bambino.add(bean);
}
int i=0;
int j=prodotti_Bambino.size();
%>	

<div class="small-container">
        <div class="row">
        
<% for(i=0;i<j;i++){ %>

 			<%if(i>=j)break; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti_Bambino.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti_Bambino.get(i).getImg()%>">
                </a>
                    <h4><%=prodotti_Bambino.get(i).getNome() %></h4><br>
                    <p><%=prodotti_Bambino.get(i).getPrezzo() %>0 €</p>
            </div>
            <% i++; if(i>=j)break;%>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti_Bambino.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti_Bambino.get(i).getImg()%>">
                </a>
                    <h4><%=prodotti_Bambino.get(i).getNome() %></h4><br>
                    <p><%=prodotti_Bambino.get(i).getPrezzo() %>0 €</p>
            </div>
            <% i++; if(i>=j)break;%>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti_Bambino.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti_Bambino.get(i).getImg()%>">
                </a>
                <h4><%=prodotti_Bambino.get(i).getNome() %></h4><br>
                <p><%=prodotti_Bambino.get(i).getPrezzo() %>0 €</p>  
            </div>
            <% i++; if(i>=j)break;%>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti_Bambino.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti_Bambino.get(i).getImg()%>">
                </a>
                <h4><%=prodotti_Bambino.get(i).getNome() %></h4><br>
                <p><%=prodotti_Bambino.get(i).getPrezzo() %>0 €</p>
                
            </div>
<%} %>
        </div>
</div>
<%@ include file = "footer.html"%>
    
</body>
</html>