<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html >
<head>
	<%@ page import="java.util.*" %>
	<%@ page import="model.ProductModelM" %>
	<%@ page import="model.ItemBean" %>
	<%@ page import="java.util.Collection" %>
	<%@ include file="navbar.jsp" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/styleHome.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.3/css/fontawesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" >
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <title>Shoes&amp;Shoes</title>
<%    
    ProductModelM prodModel= new ProductModelM();
	Collection<?> allProd = prodModel.doRetrieveAll("idprodotto");
	ArrayList<ItemBean> prodotti=new ArrayList<ItemBean>();
	ArrayList<ItemBean> prod_futuri=new ArrayList<ItemBean>();
	Iterator<?> it = allProd.iterator();
	while (it.hasNext()) {
		ItemBean bean = (ItemBean) it.next();
		if(bean.getCategoria() != 0)
        	prodotti.add(bean);	
	}
	
	Collection<?> allProd2 = prodModel.doRetrieveAll("idprodotto");
	it = allProd2.iterator();
	while (it.hasNext()) {
		ItemBean bean = (ItemBean) it.next();
		if(bean.getCategoria() == 0)
			prod_futuri.add(bean);	
	}
	
	int size=prodotti.size();
	int j=12;
	int prova=45;
%>
    
    
</head>
<body>
	<div class="header">
    <div class="containerhome">
        <div class="row">
            <div class="col-2">
                <h1>Nike Sportswear</h1>
                <p>Air Max 90 - Sneakers basse</p>
                <a href="ProdSelected.jsp?id=<%=prova %>" class="btn">Guarda Ora   &#10132;</a>
            </div>
            <div class="col-2">
                <img src="img/nike.png" >
            </div>
        </div>
    </div>
    </div>
    <!-- 3 CATEGORIE: UOMO / DONNA / BAMBINO -->
    <div class="categories">
        <div class="small-container">
            <h2 class="title">Collezioni</h2>
            <div class="row">
                <div class="col-3">
                    <a href="Uomo.jsp"><img src="img/catUomo2.jpg"></a>
                </div>
                <div class="col-3">
                    <a href="donna.jsp"><img src="img/catDonna2.jpg"></a>
                </div>
                <div class="col-3">
                    <a href="bambino.jsp"><img src="img/catBambino4.jpg"></a>
                </div>
            </div>
        </div>
    </div>
    
    <div class="small-container">
        <h2 class="title">Prodotti Futuri</h2>
        <div class="row">
        <% for(int k=0;k<8;k++){ %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prod_futuri.get(k).getId_Prodotto()%>" >
                    <img src="<%=prod_futuri.get(k).getImg()%>">
                </a>
                <h4><%=prod_futuri.get(k).getNome() %></h4><br>
                <p><%=prod_futuri.get(k).getPrezzo() %>0 €</p>
            </div>
            <% k++; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prod_futuri.get(k).getId_Prodotto()%>" >
                    <img src="<%=prod_futuri.get(k).getImg()%>">
                </a>
                <h4><%=prod_futuri.get(k).getNome() %></h4><br>
                <p><%=prod_futuri.get(k).getPrezzo() %>0 €</p>
            </div>
            <% k++; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prod_futuri.get(k).getId_Prodotto()%>" >
                    <img src="<%=prod_futuri.get(k).getImg()%>">
                </a>
                <h4><%=prod_futuri.get(k).getNome() %></h4><br>
                <p><%=prod_futuri.get(k).getPrezzo() %>0 €</p>
            </div>
            <% k++; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prod_futuri.get(k).getId_Prodotto()%>" >
                    <img src="<%=prod_futuri.get(k).getImg()%>">
                </a>
                <h4><%=prod_futuri.get(k).getNome() %></h4><br>
                <p><%=prod_futuri.get(k).getPrezzo() %>0 €</p>
            </div>
        <%} %>
        </div>
        
        
        <h2 class="title">Ultimi Prodotti</h2>
        <div class="row">
        <% for(int i=size-12;i<size;i++){ %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti.get(i).getImg()%>">
                </a>
                <h4><%=prodotti.get(i).getNome() %></h4><br>
                <p><%=prodotti.get(i).getPrezzo() %>0 €</p>
            </div>
            <% i++; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti.get(i).getImg()%>">
                </a>
                <h4><%=prodotti.get(i).getNome() %></h4><br>
                <p><%=prodotti.get(i).getPrezzo() %>0 €</p>
            </div>
            <% i++; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti.get(i).getImg()%>">
                </a>
                <h4><%=prodotti.get(i).getNome() %></h4><br>
                <p><%=prodotti.get(i).getPrezzo() %>0 €</p>
            </div>
            <% i++; %>
            <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotti.get(i).getId_Prodotto()%>" >
                    <img src="<%=prodotti.get(i).getImg()%>">
                </a>
                <h4><%=prodotti.get(i).getNome() %></h4><br>
                <p><%=prodotti.get(i).getPrezzo() %>0 €</p>
            </div>
        <%} %>
        </div>
        
    </div>
</body>
<%@ include file = "footer.html"%>
</html>