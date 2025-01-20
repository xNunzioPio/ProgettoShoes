<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <%@ include file = "navbar.jsp"%>
<head>

	<link rel="stylesheet" type="text/css" href="CSS/regCss.css">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Registrazione</title>
</head>


<body class="bodyReg">
		<form id="registrazione" action="Registration" method="post" onsubmit="event.preventDefault(); validate(this)">

   		<div class="registrazione">

        	<div class="nome">
            	<strong>Nome:</strong><br>
            	<input type="text" id="inpNome" name="nome" maxlength="30" required autofocus>
            	<br><span id="spanNome">&nbsp;</span>
            </div>
        <div>
        	<span></span>
        </div>
        <div class="cognome">
            <strong>Cognome:</strong><br>
            <input type="text" name="cognome" maxlength="30" required autofocus>
            <br><span id="spanCogn">&nbsp;</span><br>
        </div>
        <div class="data">
            <strong>Data di nascita:</strong><br>
            <input type="date" name="data_nascita" required autofocus><br><br>
        </div>
        <div class="città">
            <strong>Citt&agrave;:</strong><br>
            <input type="text" name="citta" maxlength="30" required autofocus>
            <br><span id="spanCit">&nbsp;</span>
        </div>
        <div class="Cap">
            <strong>Cap:</strong><br>
            <input type="text" name="cap" maxlength="5" required autofocus><br>
            <span id="spanCap">&nbsp;</span>
        </div>
        <div class="indirizzo">
            <strong>Indirizzo:</strong><br>
            <input type="text" name="indirizzo" maxlength="50" required autofocus>
            <br><span id="spanInd">&nbsp;</span>
        </div>
        <div class="tel">
            <strong>Telefono:</strong><br>
            <input type="text" name="telefono" maxlength="10" required autofocus><br>
            <span id="spanTel">&nbsp;</span>
        </div>
        <div class="email">
            <strong>Email:</strong><br>
            <input type="email" name="email" id="email" maxlength="50" required autofocus ><br>
            <span id="spanEmail">&nbsp;</span>
        </div>
        <div class="pass">
            <strong>Password:</strong><br>
            <input type="password" name="psw" maxlength="20" required autofocus><br>
            <span id="spanPass">&nbsp;</span>
        </div>
        <div class="botton">
            <input type="submit" value="Registrati">
        </div>
    </div>

</form>


<script type="text/javascript">
$(document).ready(function() { $("#email").keyup(function(){
var email = this.id;
$.ajax({ type: "POST",
url: "controlloEmail",
data: email+"="+this.value,
success: function(response){
if(response=='Email disponibile')
{
$("#spanEmail").html(' '+response).css("color","green");
}
else
{
$("#spanEmail").html(' '+response);
}
}
});
});
});

$(".inpNome input").on("focus",function(){
        if($(this).val() == "")
        $("#spanNome").empty;
      });
</script>
<script type="text/javascript">


function checkNome(inputtxt) {
	var name = /^[A-Za-z ]{2,}$/;
	if(inputtxt.value.match(name)) 
		return true

	return false;	
}
function checkCognome(inputtxt) {
	var name = /^[A-Za-z ']{2,}$/;
	if(inputtxt.value.match(name)) 
		return true

	return false;	
}

function checkCitta(inputtxt) {
	var cit=/^[A-Za-z ]{2,}$/;
	if(inputtxt.value.match(cit)) 
		return true

	return false;	
}

function checkCap(inputtxt) {
	var cap=/^\d{5}$/;
	if(inputtxt.value.match(cap))
		return true;
	return false;
}

function checkIndirizzo(inputtxt) {
	var indirizzo=/^[A-Za-z0-9 ']{6,}$/;
	if(inputtxt.value.match(indirizzo)) 
		return true

	return false;	
}

function checkPhonenumber(inputtxt) {
	var phoneno = /^([0-9]{3}[-\s\.]{0,1}[0-9]{6,7})$/;
	if(inputtxt.value.match(phoneno)) 
		return true;
	
	return false;
}

function checkEmail(inputtxt) {
	var email = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputtxt.value.match(email)) 
		return true;
	
	return false;	
}

function checkPassword(inputtxt) {
	var pass=/^[A-Za-z0-9.]{5,}$/;
	if(inputtxt.value.match(pass)) 
		return true;
	
	return false;
}


function validate(obj) {	
	var valid = true;	
	
	var name = document.getElementsByName("nome")[0];
	if(!checkNome(name)) {
		valid = false;
		document.getElementById("spanNome").innerHTML = "Solo caratteri alfabetici richiesti";
		name.classList.add("error");
	} else {
		document.getElementById("spanNome").innerHTML="&nbsp;";
		name.classList.remove("error");
	}
	
	var surname = document.getElementsByName("cognome")[0];
	if(!checkCognome(surname)) {
		valid = false;
		document.getElementById("spanCogn").innerHTML = "Solo caratteri alfabetici richiesti";
		surname.classList.add("error");
	} else {
		document.getElementById("spanCogn").innerHTML="&nbsp;";
		surname.classList.remove("error");
	}
	
	var citta=document.getElementsByName("citta")[0];
	if(!checkCitta(citta)){
		valid=false;
		document.getElementById("spanCit").innerHTML = "Solo caratteri alfabetici richiesti";
		citta.classList.add("error");
	} else {
		document.getElementById("spanCit").innerHTML="&nbsp;";
		citta.classList.remove("error");
	}
	
	var cap = document.getElementsByName("cap")[0];
	if(!checkCap(cap)) {
		valid = false;
		document.getElementById("spanCap").innerHTML = "Numero di 5 cifre";
		cap.classList.add("error");
	} else {
		document.getElementById("spanCap").innerHTML="&nbsp;";
		cap.classList.remove("error");
	}
	
	var indirizzo = document.getElementsByName("indirizzo")[0];
	if(!checkIndirizzo(indirizzo)) {
		valid = false;
		document.getElementById("spanInd").innerHTML = "Formato non valido";
		indirizzo.classList.add("error");
	} else {
		document.getElementById("spanInd").innerHTML="&nbsp;";
		indirizzo.classList.remove("error");
	}
	
	var numbers = document.getElementsByName("telefono");
	for(var i=0; i < numbers.length; i++) {
		if(!checkPhonenumber(numbers[i])) {
			valid = false;
			document.getElementById("spanTel").innerHTML = "Numero di 9-10 cifre ammesso";
			numbers[i].classList.add("error");
		} else  {
			document.getElementById("spanTel").innerHTML="&nbsp;";
			numbers[i].classList.remove("error");
		}
	}
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		document.getElementById("spanEmail").innerHTML = "Email non valida";
		email.classList.add("error");
	} else {
		document.getElementById("spanEmail").innerHTML="&nbsp;";
		email.classList.remove("error");
	}
	
	var pass = document.getElementsByName("psw")[0];
	if(!checkPassword(pass)) {
		valid = false;
		document.getElementById("spanPass").innerHTML = "Almeno 5 caratteri alfanumerici con il '.' ammesso";
		pass.classList.add("error");
	} else {
		document.getElementById("spanPass").innerHTML="&nbsp;";
		pass.classList.remove("error");
	}
	
	
	
	if(valid) obj.submit();
}
</script>

</body>
<%@ include file = "footer.html"%>
</html>