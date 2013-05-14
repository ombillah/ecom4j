<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="center_content marginL" style="width:800px">
         <div style="height:30px">&nbsp;</div>
         <h3>Thank you for you Order !</h3>
         <div>Please print or save this page for your record.</div>
         <div style="height:20px">&nbsp;</div>
         <div class="register_hdr_block">Billing and Shipping Information</div>
	  <div style="height:20px">&nbsp;</div>
	  <div style="height:175px">
		  <div style="width:250px; margin: 0px 100px 0px 50px" class="floatL">
			<b>Bill to:</b> <br/>
			${ order.billingAddress.firstName } ${ order.billingAddress.lastName } <br/>
			${ order.billingAddress.streetAddress }  <br/>
			${ order.billingAddress.addressLine2 } <br/> 
			${ order.billingAddress.city }, ${ order.billingAddress.state } , ${ order.billingAddress.zipCode } <br/>
			${ order.billingAddress.telephone }<br/>
			${ order.billingAddress.emailAddress }<br/>
			
			<div style="height:10px">&nbsp;</div>
			Card: XXXXXXXXXXX${fn:substring(order.paymentCard.cardNumber, 12, 16)} <br/>
			expiration date: ${order.paymentCard.expirationMonth } / ${order.paymentCard.expirationYear } <br/>
			billing Zip : ${ order.billingAddress.zipCode } <br/>
		  </div>
		  <div style="width:250px; margin:0px 100px 0px 50px" class="floatL">
			<b>Ship to:</b> <br/>
			${ order.shippingAddress.firstName } ${ order.shippingAddress.lastName } <br/>
			${ order.shippingAddress.streetAddress }  <br/>
			${ order.shippingAddress.addressLine2 } <br/> 
			${ order.shippingAddress.city }, ${ order.shippingAddress.state } , ${ order.shippingAddress.zipCode } <br/>
			${ order.shippingAddress.telephone }<br/>
			${ order.shippingAddress.emailAddress }<br/>
			
		  </div>
 	  </div>

	  <div style="height:10px">&nbsp;</div>
	  <div class="register_hdr_block">Order Details</div>
	  
	  <table class="cart_table" width="95%" style="border: 0px solid">
			<tr class=" ">
			  <td width="20%" rowspan=6 valign="middle">
				<div>
					<b>Order number</b> 
					<br/> 
					<fmt:formatNumber value="${order.orderID }" type="NUMBER" groupingUsed="false" minIntegerDigits="8" />
					
				</div>
				<div style="height:10px">&nbsp;</div>
				<div> 
					<b>Order Date</b> <br/>
					<c:set var="now" value="<%=new java.util.Date()%>"/>
					<fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${now}"/>
				</div>
			   </td>
		      <th width="45%">PRODUCT</th>
		      <th width="20%" align="center">QUANTITY</th>
		      <th width="15%" align="center">PRICE</th>
		    </tr>
		    <c:forEach items="${ order.cart.items }" var="item">
		    	<tr class="spaceabove ">
				  <td>${item.product.name }</td>
				  <td align="center">
					  ${item.quantity }
				  </td>
				  <td align="center">
					<span class="price">
					$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${ item.totalPrice }" />
					</span>
				  </td>
				</tr>
		    </c:forEach>
			<tr>
		      <td colspan="2" align="right"><h3>Product Subtotal</h3></td>
		      <td align="center">
		      	<span class="price ">
		      		$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${order.cart.productSubtotal }" />
		      	</span>
		      </td>
		    </tr>
			<tr>
		      <td colspan="2" align="right"><h3>Shipping Subtotal</h3></td>
		      <td align="center">
		      	<span class="price ">
		      		<c:choose>
		      			<c:when test="${order.cart.shippingCost gt 0 }">
		      				$ ${order.cart.shippingCost }
		      			</c:when>
		      			<c:otherwise>
		      				FREE
		      			</c:otherwise>
		      		</c:choose>
		      	</span>
		      </td>
		    </tr>
			<tr>
		      <td colspan="2" align="right"><h3>Order Total</h3></td>
		      <td align="center">
		      	<span class="price ">
		      		$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${order.cart.total }" />
		      	</span>
		      </td>
		    </tr>
		  </table>
		  
		  <div style="height:20px">&nbsp;</div>
		  
		  <input type="button" class="gradient_button floatR" value="Continue shopping" onclick="window.location='home.do'">

</div>







