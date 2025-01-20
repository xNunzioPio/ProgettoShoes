<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
	
	<meta charset="UTF-8">
	<title>Profilo utente</title>
	<link rel="stylesheet" href="CSS/profile.css">
	<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
	<%@ include file = "navbar.jsp"%>
</head>
<body>
<%
	String email,nome,cognome,data,cit,indirizzo,telefono,role2="";
	int idUtente,cap=-1;
	if(session.getAttribute("roleUser") != null) {
		role2=(String) session.getAttribute("roleUser");
		idUtente=(int) session.getAttribute("id");
		email=(String) session.getAttribute("email");
		nome=(String) session.getAttribute("nome");
		cognome=(String) session.getAttribute("Cognome");
		data=(String) session.getAttribute("dataNascita");
		cit=(String) session.getAttribute("citta");
		cap=(int) session.getAttribute("cap");
		indirizzo=(String) session.getAttribute("indirizzo");
		telefono=(String) session.getAttribute("telefono");
	}
	else {
		response.sendRedirect("index.html");
		  return;
	}
	
	%>

<div class="wrapper">
    <div class="left">
        <img class="img-profile" src="img/profilo.jpg" alt="user" height="240" width="240">
        <h3>&nbsp;</h3>
        <a href="MieiOrdini.jsp"><strong>Ordini</strong></a>
        <a href="Contattaci.jsp"><strong>Contattaci</strong></a>
        <a href="Logout"><strong>Logout</strong></a>
    </div>
    <div class="right">
        <div class="info">
            <h3>Profilo Utente</h3>
            <div class="info_data">
                 <div class="data data1">
                    <h4>Nome :</h4>
                    <p><%=nome %></p>
                 </div>
                 <div class="data data2">
                   <h4>Cognome :</h4>
                    <p><%=cognome %></p>
              </div>
            </div>
        </div>
        <div class="info">
            <div class="info_data">
                 <div class="data data1">
                    <h4>Data di nascita :</h4>
                    <p><%=data%></p>
                 </div>
                 <div class="data data2">
                   <h4>Città :</h4>
                    <p><%=cit %></p>
              </div>
            </div>
        </div>
        <div class="info">
            
            <div class="info_data">
                 <div class="data data1">
                    <h4>Cap :</h4>
                    <p><%=cap %></p>
                 </div>
                 <div class="data data2">
                   <h4>Indirizzo :</h4>
                    <p><%=indirizzo %></p>
              </div>
            </div>
        </div>
        <div class="info">
            <div class="info_data">
                 <div class="data data1">
                    <h4>Telefono :</h4>
                    <p><%=telefono %></p>
                 </div>

            </div>
        </div>
    </div>
</div>
<%@ include file = "footer.html"%>
</body>
</html>