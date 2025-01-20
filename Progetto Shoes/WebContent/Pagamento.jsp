<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagamento</title>
<link rel="stylesheet" href="CSS/card.css">
</head>
<body>
<div class="container">
  <form name="form" action="ProcessOrder">
    <div class="row">
      <h4>Dettagli di pagamento</h4>
      <div class="input-group input-group-icon">
        <input id="cardnumber" type="text" pattern="[0-9]{12,16}" placeholder="Numero Carta di credito" required autofocus>
      </div>
      <div class="col-half">
        <div class="input-group input-group-icon">
          <input id="cvv" type="text" placeholder="CVV" pattern="[0-9]{3,3}" required autofocus>
        </div>
      </div>
      <div class="col-half">
        <div class="input-group">
          <select>
            <option>Gennaio</option>
            <option>Febbraio</option>
            <option>Marzo</option>
            <option>Aprile</option>
            <option>Maggio</option>
            <option>Giugno</option>
            <option>Luglio</option>
            <option>Agosto</option>
            <option>Settembre</option>
            <option>Ottobre</option>
            <option>Novembre</option>
            <option>Dicembre</option>
          </select>
          <select>
            <option>2022</option>
            <option>2023</option>
            <option>2024</option>
            <option>2025</option>
            <option>2026</option>
            <option>2027</option>
            <option>2028</option>
          </select>
        </div>
      </div>
    </div>
    <div class="row">
    <button type="submit">Conferma il pagamento</button>
    </div>
  </form>
</div>
<script>

function check(inputtxt) {
	var regEx = /^5[1-5][0-9]{14}$|^2(?:2(?:2[1-9]|[3-9][0-9])|[3-6][0-9][0-9]|7(?:[01][0-9]|20))[0-9]{12}$/;
	if(inputtxt.value.match(regEx)) 
		return true

	return false;	
}


function validate(obj)
{
   var valid=false;
   var x= document.getElementById("cardnumber").value;
   if(check(x)){
	   valid=true;
   }
   if(valid){
	   obj.submit();
   }
   else
     {
	   console.log(x);
     alert("Please enter a valid credit card number.");
     }
}
</script>
</body>
</html>