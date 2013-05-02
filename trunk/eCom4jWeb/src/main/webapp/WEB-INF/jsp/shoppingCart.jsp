<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script language = "Javascript">
<!--

function changeFormAction(action){
	document.getElementById("shoppingCart").action = action;
	return true;
}

//-->
</script>
<style type="text/css">
<!--
.style3 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #000000;
}
.style4 {
	color: #CCFFFF
}
-->
</style>

<div class="center_content style3">

    
    
    <form id="shoppingCart" action= "checkout.do">
	  <table class="cartTable" width="89%" border="1" cellspacing="1">
	    <tr bgcolor="#02779D">
	      <th width="19%">Remove</th>
	      <th width="18%">Products</th>
	      <th width="29%">Product Name</th>
	      <th width="15%">QTY.</th>
	      <th width="16%">TOTAL</th>
	    </tr>
	    <c:forEach items="${items}" var="item">
	      <tr>
	        <td align="center"><input name="remove" type="checkbox" value="${item.product.productID}"/></td>
	        <td><img src="${item.product.image}" alt="ProductID" width="75" height="75" /></td>
	        <td><c:out value="${item.product.productID}" /></td>
	        <td><input name="quantity" size="5" /></td>
	        <td align="center">
	          <c:out value="${item.totalPrice}" /> 
	          $ </td>
	      </tr>
	    </c:forEach>
	    <tr>
	      <td  align="right" colspan="5"><span class="alreadyMember"><strong>Total:
	          <c:out value="${session.ShoppingCart.total}" />
	      </strong></span></td>
	    </tr>
	    <tr>
	      <td align="left" colspan="2"><input type="image" value="CONTINUE SHOPPING" label="Continue Shopping" src="images/continue.gif" onclick="return changeFormAction('continue.do')" /></td>
	      <td align="center" colspan="2"><input type="image" value="UPDATE CART" label="UPDATE CART" src="images/updateCart.gif"  align="center" onclick="return changeFormAction('updateCart.do')" /></td>
	      <td align="right" colspan="1"><input type="image" value="CHECKOUT" label="CHECKOUT" src="images/checkout.gif" /></td>
	    </tr>
	  </table>
 	<form>
</div>
