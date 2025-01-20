<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file = "navbar.jsp"%>
    <%@ page import="java.util.*" %>
    <%@ page import="model.OrdineBean" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="CSS/adminCss.css">
    <title>Pagina Amministratore</title>
</head>
<body>
    
    <div class="contenitore">

        <div class="controllo-ordine" >
            <form action="CercaOrdine"  onsubmit="event.preventDefault(); validate2(this)" >
                <fieldset >
                    <legend> Cerca Ordine </legend>
                    <div class="form1">
                        <strong>Nome:</strong>
                        <input type="text" name="nomeCliente">
                        <br><span id="spanNomeCliente">&nbsp;</span><br>
                        <strong>Cognome:</strong>
                        <input type="text" name="cognomeCliente">
                        <br><span id="spanCognomeCliente">&nbsp;</span><br>
                        <span>Dal:</span>
                        <input type="date" name="data1" required autofocus>
                        <span>Al:</span>
                        <input type="date" name="data2" required autofocus>
                        <input type="submit" class="pulsante" value="Cerca"><br><br>
                    </div>
                    <div class="ordine-Cercato">
                    	<%  ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();
                    		ordini=(ArrayList<OrdineBean>)session.getAttribute("ordiniTrovati");
                    		String risultato="";
                    		ArrayList<String> resultString=new ArrayList<String>();
                    		if(ordini==null || ordini.size() == 0){
                    			risultato="Ordine non trovato";
                    		}
                    		else {
                    			for(int i=0;i<ordini.size();i++){
                    				String r="";
                        			r+="ID-ORDINE : ";
                            		r+=ordini.get(i).getIdOrdine();
                            		r+=", ID-PAGAMENTO : ";
                            		r+=ordini.get(i).getIdPagamento();
                            		r+=", ID-SPEDIZIONE : ";
                            		r+=ordini.get(i).getIdSpedizione();
                            		r+=", DATA : ";
                            		r+=ordini.get(i).getData();
                            		r+=", IVA : ";
                            		r+=ordini.get(i).getIva();
                            		r+=" \n ";
                            		resultString.add(r);
                            	}
                    		}	 
                        %>
                        <p style="color: blue;">##########  Ordine cercato  ##########</p>
                        <%if(resultString.size()==0){ %>
                        <span><%=risultato %></span>
                        <%} else {
                        	for(int k=0;k<resultString.size();k++){%>
                        		<p><%=resultString.get(k)%></p>
                        <%	}
                        }	%>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="cerca-prodotto">
            <form action="CercaProdotto" method="post" >
                <fieldset>
                    <legend> Cerca Prodotto </legend>
                    <div class="form2">
                        <span>Nome Prodotto:</span>
                        <input type="text" name="nomeProdotto">
                        <input type="submit" class="pulsante" value="Cerca">
                        <br><span id="spanNomeProdotto">&nbsp;</span><br>
                    </div>
                    <div class="prodotto-Cercato">
                    <%  ItemBean prodotto=new ItemBean();
                    	prodotto=(ItemBean)session.getAttribute("prodottoTrovato");
                    	String ris="";
                    	if(prodotto==null || prodotto.getId_Prodotto()==null){
                    		ris="Prodotto non trovato";
                    	}
                    	else{
                    		ris+="ID: "+prodotto.getId_Prodotto()+",  NOME: "+prodotto.getNome();
                    		ris+=",  DESCRIZIONE: "+prodotto.getDescrizione()+",  PREZZO: "+prodotto.getPrezzo();
                    		ris+=",  QUANTITÀ: "+prodotto.getQuantita()+",  CATEGORIA: "+prodotto.getCategoria();
                    	}
                        %>
                        <p style="color: blue;">##########  Prodotto cercato  ##########</p>
                        <span><%=ris %></span>
                    </div>
                </fieldset>
            </form>
        </div>

        <div class="inserisci-ordine">
            <form action="InserisciProdotto" onsubmit="event.preventDefault(); validate(this)" >  
                <fieldset>
                    <legend> Inserisci Prodotto </legend>
                    <div class="nome">
                        <strong>Nome:</strong><br>
                        <input type="text" id="inpNome" name="nome" maxlength="30" required autofocus>
                        <br><span id="spanNome">&nbsp;</span>
                    </div>
                    <div class="descrizione">
                        <strong>Descrizione:</strong><br>
                        <textarea name="descrizione" rows="5" cols="30" required ></textarea>
                        <br><span id="spanDescrizione">&nbsp;</span>
                    </div>
                    <div class="prezzo">
                        <strong>Prezzo:</strong><br>
                        <input type="number" name="prezzo" min="1" max="1000" required autofocus><br>
                        <span id="spanPrezzo">&nbsp;</span>
                    </div>
                    <div class="immagine">
                        <strong>PathImg:</strong><br>
                        <input type="text" name="img" maxlength="30" required autofocus>
                        <br><span id="spanImg">&nbsp;</span>
                    </div>
                    <div class="categoria">
                        <strong>Categoria:</strong><br>
                        <input type="number" name="categoria" min="0" max="3" required autofocus><br>
                        <span id="spanCategoria">&nbsp;</span>
                    </div>
                    <div class="quantita">
                        <strong>Quantità:</strong><br>
                        <input type="number" name="quantita" min="1" max="9999" required autofocus><br>
                        <span id="spanQuantita">&nbsp;</span>
                    </div>
                    <p id="prodIns"></p><br>
                    <div class="botton">
                        <input type="submit" class="pulsante" value="Inserisci">
                    </div>    
                </fieldset>
            </form>
        </div>
        
        <div class="modifica-prodotto">
            <form action="ModificaProdotto" onsubmit="event.preventDefault(); validate4(this)" >  
                <fieldset>
                    <legend> Modifica Prodotto </legend>
                    <div class="idProdotto">
                        <span>ID Prodotto:</span>
                        <input type="number" name="idProdotto" min="1" required autofocus>
                        <br><br>
                    </div>
                    <div class="nome">
                        <strong>Nome:</strong><br>
                        <input type="text" id="inpNome" name="nome2" maxlength="30" required autofocus>
                        <br><span id="spanNome2">&nbsp;</span>
                    </div>
                    <div class="descrizione">
                        <strong>Descrizione:</strong><br>
                        <textarea name="descrizione2" rows="5" cols="30" required ></textarea>
                        <br><span id="spanDescrizione2">&nbsp;</span>
                    </div>
                    <div class="prezzo">
                        <strong>Prezzo:</strong><br>
                        <input type="number" name="prezzo2" min="1" max="1000" required autofocus><br>
                        <span id="spanPrezzo2">&nbsp;</span>
                    </div>
                    <div class="immagine">
                        <strong>PathImg:</strong><br>
                        <input type="text" id="inpImg" name="img2" maxlength="30" required autofocus>
                        <br><span id="spanImg2">&nbsp;</span>
                    </div>
                    <div class="categoria">
                        <strong>Categoria:</strong><br>
                        <input type="number" name="categoria2" min="1" max="3" required autofocus><br>
                        <span id="spanCategoria2">&nbsp;</span>
                    </div>
                    <div class="quantita">
                        <strong>Quantità:</strong><br>
                        <input type="number" name="quantita2" min="1" max="9999" required autofocus><br>
                        <span id="spanQuantita2">&nbsp;</span>
                    </div>
                    <p id="prodMod"></p><br>
                    <div class="botton">
                        <input type="submit" class="pulsante" value="Modifica">
                    </div>    
                </fieldset>
            </form>
        </div>


    </div>

    
    
    <%@ include file = "footer.html"%>
    
</body>

	<script>


        function checkCognome(inputtxt) {
            var surname = /^([a-zA-Z\xE0\xE8\xE9\xF9\xF2\xEC\x27]\s?)+$/;
            if(inputtxt.value.match(surname)) 
                return true

            return false;	
        }

        function checkNome(inputtxt) {
            var name = /^([a-zA-Z0-9\.\_\-])+$/;
            if(inputtxt.value.match(name)) 
                return true

            return false;	
        }
        function checkDescrizione(inputtxt) {
            var desc = /^([a-zA-Z0-9\.\_\-])+$/;
            if(inputtxt.value.match(desc)) 
                return true

            return false;	
        }
        function checkImg(inputtxt) {
            var img = /^([a-zA-Z0-9\.\/\_\-])+$/;
            if(inputtxt.value.match(img)) 
                return true

            return false;	
        }

        function validate2(obj){
            var valid =true;

            var name = document.getElementsByName("nomeCliente")[0];
            if(!checkCognome(name)) {
            valid = false;
            document.getElementById("spanNomeCliente").innerHTML = "Nome non valido!";
            name.classList.add("error");
            } else {
            document.getElementById("spanNomeCliente").innerHTML="&nbsp;";
            name.classList.remove("error");
            }

            var surname = document.getElementsByName("cognomeCliente")[0];
            if(!checkCognome(surname)) {
            valid = false;
            document.getElementById("spanCognomeCliente").innerHTML = "Cognome non valido!";
            surname.classList.add("error");
            } else {
            document.getElementById("spanCognomeCliente").innerHTML="&nbsp;";
            surname.classList.remove("error");
            }

            if(valid) obj.submit();

        }
     

        function validate3(obj){
            var valid =true;

            var name = document.getElementsByName("nomeProdotto")[0];
            if(!checkNome(name)) {
            valid = false;
            document.getElementById("spanNomeProdotto").innerHTML = "Nome non valido!";
            name.classList.add("error");
            } else {
            document.getElementById("spanNomeProdotto").innerHTML="&nbsp;";
            name.classList.remove("error");
            }

            if(valid) obj.submit();

        }

        function validate(obj) {	
        var valid = true;	
        
        var name = document.getElementsByName("nome")[0];
        if(!checkNome(name)) {
            valid = false;
            document.getElementById("spanNome").innerHTML = "Nome non valido!";
            name.classList.add("error");
        } else {
            document.getElementById("spanNome").innerHTML="&nbsp;";
            name.classList.remove("error");
        }
        
        var descrizione = document.getElementsByName("descrizione")[0];
        if(!checkDescrizione(descrizione)) {
            valid = false;
            document.getElementById("spanDescrizione").innerHTML = "Descrizione non valida!";
            descrizione.classList.add("error");
        } else {
            document.getElementById("spanDescrizione").innerHTML="&nbsp;";
            descrizione.classList.remove("error");
        }
        
        var img = document.getElementsByName("img")[0];
        if(!checkImg(img)) {
            valid = false;
            document.getElementById("spanImg").innerHTML = "Path Img non valido!";
            img.classList.add("error");
        } else {
            document.getElementById("spanImg").innerHTML="&nbsp;";
            img.classList.remove("error");
        }
             
        if(valid){ 
        	document.getElementById("prodIns").innerHTML="Prodotto inserito con successo!!!"
        	obj.submit();
        }
   		}
        
        function validate4(obj) {	
            var valid = true;	
            
            var name = document.getElementsByName("nome2")[0];
            if(!checkNome(name)) {
                valid = false;
                document.getElementById("spanNome2").innerHTML = "Nome non valido!";
                name.classList.add("error");
            } else {
                document.getElementById("spanNome2").innerHTML="&nbsp;";
                name.classList.remove("error");
            }
            
            var img = document.getElementsByName("img2")[0];
            if(!checkImg(img)) {
                valid = false;
                document.getElementById("spanImg2").innerHTML = "Path Img non valido!";
                img.classList.add("error");
            } else {
                document.getElementById("spanImg2").innerHTML="&nbsp;";
                img.classList.remove("error");
            }
                 
            if(valid){ 
            	document.getElementById("prodMod").innerHTML="Prodotto modificato con successo!!!"
            	obj.submit();
            }
            else{
            	document.getElementById("prodMod").innerHTML="Operazione fallita!!!"
            }
       		}

    </script>
</html>