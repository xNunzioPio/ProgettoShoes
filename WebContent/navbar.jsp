<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.ProductModelM" %>
<%@ page import="model.ItemBean" %>
<%@ page import="java.util.Collection" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" >
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="CSS/styleNav.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<%  session=request.getSession();
		String nProd=(String) session.getAttribute("numProd");
		if(nProd==null){
			nProd="0";
		}%>
    <div class="container">
        <div class="navbar header_menu">
            <div class="logo">
                <a href="home.jsp"><img src="img/SS.jpg" width="50px"></a>
            </div>
            <nav>
                <ul class="prova">
                    <li class="header_menu_item primoLi"><a href="Uomo.jsp">Uomo</a></li>
                    <li class="header_menu_item"><a href="donna.jsp">Donna</a></li>
                    <li class="header_menu_item"><a href="bambino.jsp">Bambino</a></li>
                </ul>
            </nav>
            <div class="search-box">
                <input class="search-txt" type="text" id="searchBar" name="ricerca" placeholder="Cerca">
                <a id="btn-cerca" class="search-btn" onclick="cerca2()"  >
                    <i class="fas fa-search" ></i>
                </a>
            </div> 
            <div class="header_menu_img primaIcon"><a href="Profile.jsp"><i class="bi bi-person-circle"></i><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 13">
                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                </svg></i></a>
            </div>
            <div class="header_menu_img cart"><a href="VisualizzaCarrello.jsp"><i class="bi bi-bag"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag" viewBox="0 0 16 16">
                <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
                </svg><span><%=nProd %></span></i></a>
            </div>
            <div class="header_menu_img icon_setting">
                <a href="AdminPage.jsp">
                    <i class="bi bi-gear" 
                 <% String role="";
					session=request.getSession();
                    role=(String) session.getAttribute("roleUser");
                    if(role==null){%>style="display:none"
                    <%}else {
                    	if(role.equals("admin")){%> style="display:inline"<%} 
                    	if(role.equals("user")){%> style="display:none" <%}
                    	}%>
                    ><svg xmlns="http://www.w3.org/2000/svg" width="31" height="31" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
                        <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
                        <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
                      </svg></i>
                </a>
            </div>
            <a href="" class="header_icon-bar">
                <span></span>
                <span></span>
                <span></span>
            </a>
        </div>
    </div>
    
    <p class="demo"></p>

</body>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
			<script>
			    $(document).ready(function(){
			        $(".header_icon-bar").click(function(e){
			            $(".container").toggleClass('is-open');
			            $(".prova").toggleClass('is-open');
			            $(".search-box").toggleClass('is-open-search');
			            $(".header_menu_img").toggleClass('is-open-img');
			            e.preventDefault();
			        });
			    });
			</script>
	
			<script>    
			    function cerca2(){
			    	var pag="ProdottiCercati.jsp?text=";
			    	var text=document.getElementById("searchBar").value;
			    	pag+=text;
			    	window.open(pag);
			    }
			</script>

</html>
