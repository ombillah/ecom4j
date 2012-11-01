<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <div class="center_content">
	  <div class="address">
       	<div class="shippingAddress" >
       	  <p><u><em><strong>Shipping Address:</strong></em></u></p>
	      <div class="shippingInfo" >

         <p><c:out value="${userName}" /></p>
       	  <p><c:out value="${shippingStreet}" /></p>
       	  <p><c:out value="${shippingCity}" />,
       	   	 <c:out value="${shippingState}" />,
       	   	<c:out value="${shippingZip}" /></p>
         </div>
       	</div>
            <div class="billingAddress" >
              <p><u><em><strong>Billing Address:</strong></em></u></p>
              <p><c:out value="${userName}" /></p>
              <p><c:out value="${billingStreet}" /></p>
              <p><c:out value="${billingCity}" />,
               <c:out value="${billingState}" />,
                <c:out value="${billingZip}" /></p>
            </div>
            <div class="order" >      
            <u><em><strong>Order Summary:</strong></em></u><br />
            <br />
            <table width="84%" border="0" cellspacing="1">
	             <tr bgcolor="#02779D">
	                <th width="21%"><span class="alreadyMember">Products</span></th>
	                <th width="35%"><span class="alreadyMember">Product ID</span></th>
	                <th width="14%"><span class="alreadyMember">QTY.</span></th>
	                <th width="19%"><span class="alreadyMember">TOTAL</span></th>
	             </tr>
	       	 	 <c:forEach items="${items}" var="item">
	                <tr bgcolor="#F7F7F7">
	                  <td><img src="${item.product.src}" alt="ProductID" width="75" height="75" /></td>
	                  <td ><c:out value="${item.product.productID}" /></td>
	                  <td><c:out value="${item.quantity}" /></td>
	                  <td align="center" >
	                    <c:out value="${item.totalPrice}" /> $</td>
	                </tr>
	             </c:forEach>
	             <tr class="product_title">
	                <td  align="right" colspan="5">Total = <c:out value="${session.ShoppingCart.total}" /></td>
	             </tr>
	             <tr>
		             <form id="shoppingCart" action="checkout.do" method="post" >  
		  				<td colspan="3"><input type="submit" align="right" value="Edit Cart" onclick="return changeFormAction()" /></td>
		  				<td colspan="2"><input type="submit" value="Place Order" align="right"/></td>
		  			 </form>
	    		 </tr>
    		</table>
    	
            </div>				
		  </div>
  </div>
