<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center_content">
    <c:forEach items="${products}" var="product">
      <div class="prod_box">
        <div class="top_prod_box"></div>
        <div class="center_prod_box">
        
	        <form action="details.do" method="GET" >
			   	<input type="hidden" name="productID" value="${product.productID}" />
	          <div class="product_title"> ${product.productID}</div>
	          <div class="product_img"> <input type="image" align="center" src="${product.src}" cssStyle="width:130px; height:130px;"/></div>
			</form>
     
          	<div class="prod_price"> <span class="price">$ ${product.unitPrice}</span></div>
        </div>
        <div class="prod_details_tab">
        	 <a href="details.do?productID=${product.productID}" class="prod_details">details</a> </div>
      </div>
     </c:forEach>
</div>