<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ecom4j Store</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="iecss.css" />
<script type="text/javascript" src="/ecom4j/js/boxOver.js"></script>
</head>
<body>

<div id="main_container">
  			<jsp:include page="header.jsp"  flush="false" />
      		<jsp:include page="categories.jsp"  flush="false" />
    <!-- end of left content -->
    
    <div class="center_content">
            <div class="center_title_bar">Search Result</div>
    <div class="products" style="width:600px; height:600px;">
      <c:forEach items="${result}" var="product">
       <div class="prod_box">
        <div class="top_prod_box"></div>
        <div class="center_prod_box">
        <form action="details.do" >
		   	<input type="hidden" name="productID" value="${product.productID}" />
          <div class="product_title"> ${product.productID}</div>
          <div class="product_img"> <input  type="image" align="center" src="${product.src}" cssStyle="width:130px; height:130px;"/></div>
		</form>
     
          <div class="prod_price"> <span class="price">$ ${product.salePrice}</span></div>
        </div>
        <div class="prod_details_tab">
        	 <a href="details.do?productID=${productID}" class="prod_details">details</a> </div>
      </div>
     </c:forEach>
         <div class="pagination" style="width:500px; height:auto; float:right; position: inherit;">
      <div class="oferta_pagination">
		<c:forEach items="${pages}" var="page" >
			<c:choose> 
	 			<c:when test="${page == pageNum}" > 
					<a href="search.do?pageNum=${page}&keyword=${keyword}"><span class="current">${page}</span></a>
	  			</c:when> 
	  			<c:otherwise> 
					<a href="search.do?pageNum=${page}&keyword=${keyword}">${page}</a>
				</c:otherwise> 
			</c:choose>  
       </c:forEach>
        </div>
     </div>
   </div>
  </div>
    <!-- end of center content -->
		<jsp:include page="cart.jsp"  flush="false" />
		<jsp:include page="footer.jsp"  flush="false" />

<!-- end of main_container -->
</div>
</body>
</html>
