<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contattaci</title>
<link rel="stylesheet" href="CSS/contattaci.css">
<%@ include file = "navbar.jsp"%>
</head>
<body>
	<div class="cont">
		<h1>Contattaci</h1>
		<p><strong>Email:</strong> Shoes&amp;Shoes&commat;ecommerce.com</p>
		<p><strong>Tel:</strong> 059-123456</p>
		<p><strong>Fax:</strong> 055-123456</p>
		<p><strong>Sede:</strong>Via Giovanni Paolo II, 132 - 84084 Fisciano (SA)</p>
		
		<h2>Possiamo aiutarti?</h2>
		<form>
		    <div class="tableRow">
		        <strong>Nome:</strong><br>
		        <input type="text" name="nome" maxlength="30" required>
		    </div>
		    <div class="tableRow">
		        <strong>Cognome:</strong><br>
		        <input type="text" name="cognome" maxlength="30" required>
		    </div>
		    <div class="tableRow">
		        <strong>Telefono:</strong><br>
		        <input type="text" name="telefono" maxlength="10" required><br>
		    </div>
		    <div class="tableRow">
		        <strong>Email:</strong><br>
		        <input type="email" name="email" maxlength="50" required><br><br>
		    </div>
		    <div>
		        <strong>Seleziona un argomento:</strong><br>
		        <select name="argomento" id="arg">
		            <option value="Reso di un ordine ">Reso di un Ordine</option>
		            <option value="Pagamento ">Pagamento</option>
		            <option value="Spedizione ">Spedizione</option>
		            <option value="Altro " selected>Altro</option>
		        </select>
		
		    </div>
		    <div>
		        <br><strong>Inviaci un messaggio:</strong><br>
		        <textarea name="comments" rows="8" cols="40" required placeholder="Facci sapere come possiamo aiutarti!"></textarea>
		        <p>
		            <input class="btn-invia" type="submit" value="Invia">
		        </p>
		    </div>
		
		</form>
	</div>	
		
<%@ include file = "footer.html"%>
</body>
</html>