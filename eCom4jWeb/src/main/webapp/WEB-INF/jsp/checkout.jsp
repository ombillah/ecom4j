<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

.register_hdr_block {
	width:275px;
}

</style>
   <div class="center_content" style="width:975px">
   	<form:form action="checkout-confirm.do" method="post" modelAttribute="order">
        <div class="floatL" style="width:250px; margin:0px 75px 0px 25px" >
          <div style="height:30px">&nbsp</div>
          <div class="register_hdr_block">Step 1: Billing Information</div>
          <div style="height:20px">&nbsp</div>
           <div class="errormsg">
		    <form:errors path="billingAddress.firstName" element="li"/>
		    <form:errors path="billingAddress.lastName" element="li" />
		    <form:errors path="billingAddress.streetAddress" element="li"/>
		    <form:errors path="billingAddress.city" element="li"/>
		    <form:errors path="billingAddress.state" element="li"/>
		    <form:errors path="billingAddress.zipCode" element="li"/>
		  </div>
          	<div>First Name *</div>
          	<div class="marginB"><form:input path="billingAddress.firstName" size="40" cssStyle="background:#F0F0F0"  /></div>
          	
          	<div>Last Name *</div>
       		<div class="marginB"><form:input path="billingAddress.lastName" size="40" cssStyle="background:#F0F0F0"  /></div>
       		
       		<div>Street Address *</div>
       		<div class="marginB"><form:input path="billingAddress.streetAddress" size="40" cssStyle="background:#F0F0F0"  /></div>
       		
       		<div>Address 2</div>
       		<div class="marginB"><form:input path="billingAddress.addressLine2" size="40" cssStyle="background:#F0F0F0"  /></div>
       		
       		<div>City *</div>
       		<div class="marginB"><form:input path="billingAddress.city" size="40" cssStyle="background:#F0F0F0"  /></div>
       		
       		<div>State *</div>
			<div class="marginB">
				<form:select path="billingAddress.state" cssStyle="background:#F0F0F0" >
				  <form:option  value="">Select a State</form:option>
				  <form:options items="${states }"/>
				</form:select> 
			</div>
       		
       		<div>Zip Code *</div>
       		<div class="marginB"><form:input path="billingAddress.zipCode" size="10"  cssStyle="background:#F0F0F0"  /></div>
       		
       		<div>Phone Number</div>
       		<div class="marginB"><form:input path="billingAddress.telephone" size="20" cssStyle="background:#F0F0F0"  /></div>
        </div>
        <div class="floatL marginL" style="width:250px">
          <div style="height:30px">&nbsp</div>
          <div class="register_hdr_block">Step 2: Shipping Information</div>
          <div style="height:20px">&nbsp</div>
           <div class="errormsg">
		    <form:errors path="shippingAddress.firstName" element="li"/>
		    <form:errors path="shippingAddress.lastName" element="li" />
		    <form:errors path="shippingAddress.streetAddress" element="li"/>
		    <form:errors path="shippingAddress.city" element="li"/>
		    <form:errors path="shippingAddress.state" element="li"/>
		    <form:errors path="shippingAddress.zipCode" element="li"/>
		   </div>
	   		<div>First Name *</div>
	   		<div class="marginB"><form:input path="shippingAddress.firstName" size="40" cssStyle="background:#F0F0F0"  /></div>
	   		
	   		<div>Last Name *</div>
	   		<div class="marginB"><form:input path="shippingAddress.lastName" size="40" cssStyle="background:#F0F0F0"  /></div>
	   		
	   		<div>Street Address *</div>
	   		<div class="marginB"><form:input path="shippingAddress.streetAddress" size="40" cssStyle="background:#F0F0F0"  /></div>
	   		
	   		<div>Address 2</div>
	   		<div class="marginB"><form:input path="shippingAddress.addressLine2" size="40" cssStyle="background:#F0F0F0"  /></div>
	   		
	   		<div>City *</div>
	   		<div class="marginB"><form:input path="shippingAddress.city" size="40" cssStyle="background:#F0F0F0"  /></div>
	   		
	   		<div>State *</div>
	   		<div class="marginB">
				<form:select path="shippingAddress.state" cssStyle="background:#F0F0F0" >
				<form:option  value="">Select a State</form:option>
				<form:options items="${states }"/>
				</form:select> 
			</div>
	      	
	      	<div>Zip Code *</div>
	      	<div class="marginB"><form:input path="shippingAddress.zipCode" size="10"  cssStyle="background:#F0F0F0"  /></div>
	   		
	   		<div>Phone Number</div>
	   		<div class="marginB"><form:input path="shippingAddress.telephone" size="20" cssStyle="background:#F0F0F0"  /></div>
          	
          	
       </div>
       <div class="floatL" style="margin-left:75px">  
         <div style="height:30px">&nbsp</div>
         <div class="register_hdr_block">Step 3: Payment Information</div>
          <div style="height:20px">&nbsp</div>
           <div class="errormsg">
		    <form:errors path="paymentCard.cardType" element="li"/>
		    <form:errors path="paymentCard.cardNumber" element="li" />
		    <form:errors path="paymentCard.ExpirationMonth" element="li"/>
		    <form:errors path="paymentCard.ExpirationYear" element="li"/>
		    <form:errors path="paymentCard.ccv" element="li"/>
		    <form:errors path="paymentCard.nameOnCard" element="li"/>
		   </div>
		    <div class="marginB"><b>Payment Info is disabled for demonstration.</b></div>
		   
	   		<div>Card Type</div>
	   		<div class="marginB">
	   			<form:select path="paymentCard.cardType" cssClass="gray_dropdown" disabled="true" >
					<form:option  value="visa">Visa</form:option>
					<form:option  value="masterCard">Master Card</form:option>
					<form:option  value="amex">American Express</form:option>
				</form:select> 
				<img alt="" src="images/creditcardlogos.jpg" width=100 height=20>
	   		</div>

	   		<div>Name on card</div>
	   		<div class="marginB"><form:input path="paymentCard.nameOnCard" size="40" cssStyle="background:#F0F0F0" value="Test Tester" disabled='true'/></div>

	   		
	   		<div>Card Number</div>
	   		<div class="marginB"><form:input path="paymentCard.cardNumber" size="40" cssStyle="background:#F0F0F0" value="11111111111111" disabled="true" /></div>
	   		
	   		<div>Expiration Date</div>
	   		<div class="marginB">
	   			<form:select path="paymentCard.expirationMonth" cssClass="gray_dropdown" disabled="true"   >
					<form:option  value="1">1</form:option>
					<form:option  value="2">2</form:option>
					<form:option  value="3">3</form:option>
					<form:option  value="4" selected="true" >4</form:option>
					<form:option  value="5">5</form:option>
					<form:option  value="6">6</form:option>
					<form:option  value="7">7</form:option>
					<form:option  value="8">8</form:option>
					<form:option  value="9">9</form:option>
					<form:option  value="10">10</form:option>
					<form:option  value="11">11</form:option>
					<form:option  value="12">12</form:option>
				</form:select> 
				<form:select path="paymentCard.expirationYear"  cssClass="gray_dropdown" disabled="true"  >
					<form:option  value="2014">2014</form:option>
					<form:option  value="2015" selected="true">2015</form:option>
					<form:option  value="2016">2016</form:option>
					<form:option  value="2017">2017</form:option>
					<form:option  value="2018">2018</form:option>
					<form:option  value="2019">2019</form:option>
				</form:select> 
	   		</div>
	   		
	   		<div>CCV number</div>
	   		<div class="marginB"><form:input path="paymentCard.ccv" size="5" cssStyle="background:#F0F0F0" value="123" disabled="true" /></div>
	   		
          	<div style="height:30px">&nbsp</div>
	        <div class="register_hdr_block">Order Summary</div>
	        <div style="height:20px">&nbsp</div>
	        <table border="0">
	        	<tr>
	        		<td width=150 >Products (${fn:length(sessionScope.shoppingCart.items)} items)</td>
	        		<td width=120 align="right">
	        			<span class="price" >
	        				$ ${sessionScope.shoppingCart.productSubtotal} 
	        			</span>
	        		</td>
	      		</tr>
	        	<tr>
	        		<td width=150 >Shipping</td>
	        		<td width=120  align="right">
	        			<span class="price" >
				      		<c:choose>
				      			<c:when test="${sessionScope.shoppingCart.shippingCost gt 0 }">
				      				$ ${sessionScope.shoppingCart.shippingCost }
				      			</c:when>
				      			<c:otherwise>
				      				FREE
				      			</c:otherwise>
				      		</c:choose>
			      		</span>

	        		</td>
	      		</tr>
	      		<tr>
	        		<td colspan="2"></td>
	      		</tr>
	        	<tr>
	        		<td width=150 ><b>Grand Total</b></td>
	        		<td width=120 align="right">
	        			
	        			<span class="price" >
	        				$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${sessionScope.shoppingCart.total }" /> 
	        			</span>
	        		</td>
	      		</tr>
	      	    <tr>
	      	    	<td colspan="2" align="right">
	      	    		<div style="height:20px">&nbsp</div>
	      	    		<input type="button"  class="gradient_button floatL marginR" value="Back to Cart" onclick="window.location='viewcart.do'" />
	      	    		<input type="submit"  class="gradient_button_orange floatR" value="Submit Order" />		
	      	    	</td>
	      	    </tr>
	        </table>
       </div>
	</form:form>
</div>