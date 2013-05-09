<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   <div class="center_content" style="height:1300px">
   	<form:form action="checkout-confirm.do" method="post" modelAttribute="order">
        <div class="register" >
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
          <table class="register_table">
          	<tr>
          		<td width="200" align="right">First Name *</td>
          		<td width="250" align="left"><form:input path="billingAddress.firstName" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Last Name *</td>
          		<td width="250" align="left"><form:input path="billingAddress.lastName" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Street Address *</td>
          		<td width="250" align="left"><form:input path="billingAddress.streetAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Address 2</td>
          		<td width="250" align="left"><form:input path="billingAddress.addressLine2" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">City *</td>
          		<td width="250" align="left"><form:input path="billingAddress.city" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">State *</td>
          		<td width="250" align="left">
					<form:select path="billingAddress.state" cssStyle="background:#F0F0F0" >
					  <form:option  value="">Select a State</form:option>
					  <form:options items="${states }"/>
					</form:select> 
				</td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Zip Code *</td>
          		<td width="250" align="left"><form:input path="billingAddress.zipCode" size="10"  cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Phone Number</td>
          		<td width="250" align="left"><form:input path="billingAddress.telephone" size="20" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          </table>
          <div style="height:30px">&nbsp</div>
          <div class="register_hdr_block">Shipping Information</div>
          <div style="height:20px">&nbsp</div>
           <div class="errormsg">
		    <form:errors path="shippingAddress.firstName" element="li"/>
		    <form:errors path="shippingAddress.lastName" element="li" />
		    <form:errors path="shippingAddress.streetAddress" element="li"/>
		    <form:errors path="shippingAddress.city" element="li"/>
		    <form:errors path="shippingAddress.state" element="li"/>
		    <form:errors path="shippingAddress.zipCode" element="li"/>
		  </div>
          <table class="register_table">
          	<tr>
          		<td width="200" align="right">First Name *</td>
          		<td width="250" align="left"><form:input path="shippingAddress.firstName" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Last Name *</td>
          		<td width="250" align="left"><form:input path="shippingAddress.lastName" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Street Address *</td>
          		<td width="250" align="left"><form:input path="shippingAddress.streetAddress" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Address 2</td>
          		<td width="250" align="left"><form:input path="shippingAddress.addressLine2" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">City *</td>
          		<td width="250" align="left"><form:input path="shippingAddress.city" size="50" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">State *</td>
          		<td width="250" align="left">
					<form:select path="shippingAddress.state" cssStyle="background:#F0F0F0" >
					  <form:option  value="">Select a State</form:option>
					  <form:options items="${states }"/>
					</form:select> 
				</td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Zip Code *</td>
          		<td width="250" align="left"><form:input path="shippingAddress.zipCode" size="10"  cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          	<tr>
          		<td width="200" align="right">Phone Number</td>
          		<td width="250" align="left"><form:input path="shippingAddress.telephone" size="20" cssStyle="background:#F0F0F0"  /></td>
          		<td width="350" align="left"></td>
          	</tr>
          </table>
          <div style="height:30px">&nbsp</div>
         
        </div>
	</form:form>
</div>