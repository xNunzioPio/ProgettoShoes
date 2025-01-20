<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.ProductModelM" %>
<%@ page import="model.ItemBean" %>
<%@ page import="java.sql.*" %>
<%@ page import="model.DriverManagerConnectionPool" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file = "navbar.jsp"%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/productSel.css">
</head>

<body>

	<%final class GalleryBean{
		private int id;
		private String img1;
		private String img2;
		private String img3;
		private String img4;
		
		
		public GalleryBean() {

		}
		
		//METODI
		
		public void setId(int idp) {
			id=idp;
		}
		public void setImg1(String i1) {
			img1=i1;
		}
		public void setImg2(String i2) {
			img2=i2;
		}
		public void setImg3(String i3) {
			img3=i3;
		}
		public void setImg4(String i4) {
			img4=i4;
		}
		public int getId() {
			return id;
		}
		public String getImg1() {
			return img1;
		}
		public String getImg2() {
			return img2;
		}
		public String getImg3() {
			return img3;
		}
		public String getImg4() {
			return img4;
		}

	}
 %>
 <%

 final class GalleryDM {
 	
 	private static final String TABLE_NAME = "galleria";
 	
 public synchronized void doSave(GalleryBean galleryBean) throws SQLException{
 		
 		Connection con=null;
 		PreparedStatement preparedStatement = null;;
 		

 		String insertSQL = "INSERT INTO " + TABLE_NAME
 				+ " (idprodotto, img1, img2, img3, img4) VALUES (?, ?, ?, ?, ?)";
 	
 		try {
 			con=DriverManagerConnectionPool.getConnection();
 			preparedStatement = con.prepareStatement(insertSQL);
 			preparedStatement.setInt(1, galleryBean.getId());
 			preparedStatement.setString(2, galleryBean.getImg1());
 			preparedStatement.setString(3, galleryBean.getImg2());
 			preparedStatement.setString(4, galleryBean.getImg3());
 			preparedStatement.setString(5, galleryBean.getImg4());
 			
 			preparedStatement.executeUpdate();
 			
 			con.commit();
 		} 
 		finally {
 			try {
 				if(preparedStatement != null)
 					preparedStatement.close();
 			} finally {
 				DriverManagerConnectionPool.releaseConnection(con);
 			}
 		}
 	}
 	
 public synchronized GalleryBean doRetrieveByid(int id) throws SQLException{
 	Connection con=null;
 	Statement stmt=null;
 	
 	GalleryBean imgProd=new GalleryBean();
 	
 	String selectSQL="SELECT * FROM shoes.galleria where idprodotto="+id;
 	
 	try {
 		con=DriverManagerConnectionPool.getConnection();
 		stmt=con.createStatement();
 		
 		ResultSet rs=stmt.executeQuery(selectSQL);
 		
 		while(rs.next()) {
 			imgProd.setId(rs.getInt("idprodotto"));
 			imgProd.setImg1(rs.getString("img1"));
 			imgProd.setImg2(rs.getString("img2"));
 			imgProd.setImg3(rs.getString("img3"));
 			imgProd.setImg4(rs.getString("img4"));
 		}
 	} finally {
 		try {
 			if(stmt != null)
 				stmt.close();
 		} finally {
 			DriverManagerConnectionPool.releaseConnection(con);
 		}
 	}
 	return imgProd;
 	
 }

 public synchronized boolean doDelete(int id) throws SQLException {
 	Connection con=null;
 	Statement stmt=null;
 	
 	int result=0;
 	
 	String deleteSQL="DELETE FROM `shoes`.`galleria` WHERE (`idprodotto` = '"+id+"')";
 	
 	try {
 		con=DriverManagerConnectionPool.getConnection();
 		stmt=con.createStatement();
 		
 		result=stmt.executeUpdate(deleteSQL);
 	}
 	finally {
 		try {
 			if(stmt != null)
 				stmt.close();
 		}finally {
 			DriverManagerConnectionPool.releaseConnection(con);
 		}
 	}
 	return (result!=0);
 }

 public synchronized Collection<GalleryBean> doRetrieveAll() throws SQLException {
 	Connection con=null;
 	Statement stmt=null;
 	
 	Collection<GalleryBean>imgProdotti=new LinkedList<GalleryBean>();
 	
 	String selectSQL="SELECT * FROM shoes.galleria";
 	
 	try {
 		con=DriverManagerConnectionPool.getConnection();
 		stmt=con.createStatement();
 		
 		ResultSet rs=stmt.executeQuery(selectSQL);
 		
 		while(rs.next()) {
 			GalleryBean imgProdotto=new GalleryBean();
 			imgProdotto.setId(rs.getInt("idprodotto"));
 			imgProdotto.setImg1(rs.getString("img1"));
 			imgProdotto.setImg2(rs.getString("img2"));
 			imgProdotto.setImg3(rs.getString("img3"));
 			imgProdotto.setImg4(rs.getString("img4"));
 			imgProdotti.add(imgProdotto);
 		}
 	} finally {
 		try {
 			if(stmt!=null)
 				stmt.close();
 		} finally {
 			DriverManagerConnectionPool.releaseConnection(con);
 		}
 	}
 	return imgProdotti;	
 }


 }

 %>
	<%
		String id=request.getParameter("id");
	    ProductModelM prodModel= new ProductModelM();
	    Collection<?> prodotti = prodModel.doRetrieveAll("idprodotto");
	    ArrayList<ItemBean> prodotto=new ArrayList<ItemBean>();
	    Iterator<?> it = prodotti.iterator();
	    while (it.hasNext()) {
	    	ItemBean bean = (ItemBean) it.next();
	    	if(bean.getId_Prodotto().equals(id))
	    		prodotto.add(bean);
	    }
	    GalleryDM cercaImgProd=new GalleryDM();
	    Collection<?> galleria=cercaImgProd.doRetrieveAll();
	    ArrayList<GalleryBean> imgProdotti=new ArrayList<GalleryBean>();
	    Iterator<?> it2 = galleria.iterator();
	    int idprodotto=Integer.parseInt(id);
	    while (it2.hasNext()) {
	    	GalleryBean beanImg = (GalleryBean) it2.next();
	    	if(beanImg.getId()==idprodotto)
	    		imgProdotti.add(beanImg);
	    }
	    prodotti = prodModel.doRetrieveAll("idprodotto");
	    ArrayList<ItemBean> prodottiSimili=new ArrayList<ItemBean>();
	    it = prodotti.iterator();
	    while (it.hasNext()) {
	    	ItemBean bean = (ItemBean) it.next();
	    	if(!(bean.getId_Prodotto().equals(id)) && bean.getCategoria()==prodotto.get(0).getCategoria())
	    		prodottiSimili.add(bean);
	    }
	    int totalProdSimili=prodottiSimili.size();
	    Random rand=new Random();
	    int j;
	    for(int i=0;i<4;i++){
	    	j=rand.nextInt(prodottiSimili.size());
	    	prodotto.add(prodottiSimili.get(j));
	    	prodottiSimili.remove(j);
	    }

	 %>
	    
    <!-- Detagli per il prodotto selezionato -->

        <div class="small-container single-product">
            <div class="row">
                <div class="col-2">
                    <img  id="ProductImg" src="<%=prodotto.get(0).getImg()%>" width="100%">
                    <div class="small-img-row">
                        <div class="small-img-col">
                            <img class="small-img" src="<%=prodotto.get(0).getImg()%>" width="100%">
                        </div>
                        <div class="small-img-col">
                            <img class="small-img" src="<%=imgProdotti.get(0).getImg2()%>" width="100%">
                        </div>
                        <div class="small-img-col">
                            <img class="small-img" src="<%=imgProdotti.get(0).getImg3()%>" width="100%">
                        </div>
                        <div class="small-img-col">
                            <img class="small-img" src="<%=imgProdotti.get(0).getImg4()%>" width="100%">
                        </div>
                    </div>
                </div>
               
                <div class="col-2 col2-right">
                    <h1 id="id" style="display:none"><%=prodotto.get(0).getId_Prodotto()%></h1>
                    <h1 id="primoh1"><%=prodotto.get(0).getNome() %></h1>
                    <h4 id="prezzo" ><%=prodotto.get(0).getPrezzo() %>0 &euro;</h4>
                    <%if(prodotto.get(0).getCategoria()!= 0){ %>
                    <form action="AggiungiAlCarrello" onsubmit="event.preventDefault(); saveDate(this)">
                    <select name="x-taglia" id="taglia">
                    <%} %>
                    <%if(prodotto.get(0).getCategoria()==1){ %>
                        <option value="37">37</option>
                        <option value="38">38</option>
                        <option value="39">39</option>
                        <option value="40">40</option>
                        <option value="41">41</option>
                        <option value="42">42</option>
                        <option value="43">43</option>
                    <% } %>
                    <%if(prodotto.get(0).getCategoria()==2){ %>
                        <option value="35">35</option>
                        <option value="36">36</option>
                        <option value="37">37</option>
                        <option value="38">38</option>
                        <option value="39">39</option>
                        <option value="40">40</option>
                        <option value="41">41</option>
                    <% } %>
                    <%if(prodotto.get(0).getCategoria()==3){ %>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                    <% } %>
                    </select>
                    <%if(prodotto.get(0).getCategoria()!= 0){ %>
                    <input id="qnt"type="number" name="qnt-Prodotto" min="1" max="9" value="1">
                    <%} %>
                    <%if(prodotto.get(0).getCategoria()!= 0){ %>
                    <input type="submit" class="btn add-cart" value="Compra">
                    <%} %>
                    </form>
                    <h3>Dettagli Prodotto</h3>
                    <p><%=prodotto.get(0).getDescrizione()%></p>
                </div>
                
            </div>
        </div>
        <!-- Prodotti simili a quello visualizzato -->
        <div class="small-container small-container2">
            <div class="row row-2">
                <h2>Prodotti Simili</h2>
            </div>
        </div>
        <div class="small-container">
            
            <div class="row">
                <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotto.get(1).getId_Prodotto()%>" >
                    <img src="<%=prodotto.get(1).getImg()%>">
                </a>
                    <h4><%=prodotto.get(1).getNome() %></h4>
                    <p><%=prodotto.get(0).getPrezzo() %>0 &euro;</p>
                </div>
                <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotto.get(2).getId_Prodotto()%>" >
                    <img src="<%=prodotto.get(2).getImg()%>">
                </a>
                    <h4><%=prodotto.get(3).getNome() %></h4>
                    <p><%=prodotto.get(0).getPrezzo() %>0 &euro;</p>
                </div>
                <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotto.get(3).getId_Prodotto()%>" >
                    <img src="<%=prodotto.get(3).getImg()%>">
                </a>
                    <h4><%=prodotto.get(3).getNome() %></h4>
                    <p><%=prodotto.get(0).getPrezzo() %>0 &euro;</p>
                </div>
                <div class="col-4">
                <a href="ProdSelected.jsp?id=<%=prodotto.get(4).getId_Prodotto()%>" >
                    <img src="<%=prodotto.get(4).getImg()%>">
                </a>
                    <h4><%=prodotto.get(4).getNome() %></h4>
                    <p><%=prodotto.get(0).getPrezzo() %>0 &euro;</p>
                </div>
            </div>
        </div>

        <script>
            var ProductImg=document.getElementById("ProductImg");
            var SmallImg= document.getElementsByClassName("small-img");

            SmallImg[0].onclick=function(){
                ProductImg.src = SmallImg[0].src;
            }
            SmallImg[1].onclick=function(){
                ProductImg.src = SmallImg[1].src;
            }
            SmallImg[2].onclick=function(){
                ProductImg.src = SmallImg[2].src;
            }
            SmallImg[3].onclick=function(){
                ProductImg.src = SmallImg[3].src;
            }
        </script>
        
        

<%@ include file = "footer.html"%>
<script>
	function saveDate(obj){
		<%session.setAttribute("idProdotto",prodotto.get(0).getId_Prodotto());  
		%>
		obj.submit();	
	}
	
</script>
</body>

</html>