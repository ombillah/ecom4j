<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
function switchShipping($elem){
	$id = $($elem).attr('id');
	
	$standardSrc = $('#shipping_standard').attr('src');
	$expressSrc = $('#shipping_express').attr('src');
	$standardNewSrc = "";
	$expressNewSrc = "";
	
	if ($id.indexOf("standard") >= 0) {
		 $standardNewSrc = $standardSrc.replace("_off", "_on");
		 $expressNewSrc = $expressSrc.replace("_on", "_off");
	}
	if ($id.indexOf("express") >= 0) {
		 $standardNewSrc = $standardSrc.replace("_on", "_off");
		 $expressNewSrc = $expressSrc.replace("_off", "_on");
	}
	$('#shipping_standard').attr('src', $standardNewSrc);
	$('#shipping_express').attr('src', $expressNewSrc);
}

</script>

<div class="center_content" style="padding-left:100px">

    <div style="padding:50px 10px 20px 10px; width:92%">
		<input type="button"  class="gradient_button floatL" value="Continue shopping" onclick="window.location='home.do'" />
		<input type="button"  class="gradient_button_orange floatR" value="Proceed with your order" onclick="" />
	</div>
    <form id="shoppingCart" action="http://localhost:8080/ecom4j/checkout.do">
	
	</form>
	
	<table class="cart_table" width="95%">
		<tr >
	      <td width="100%" colspan=5><h3><span class="marginL">1. Items added to your Cart</span></h3></td>
	    </tr>
	    <tr class="spaceabove ">
	      <th width="10%"></th>
	      <th width="20%">PRODUCT</th>
	      <th width="30%"></th>
	      <th width="20%" align="center">QUANTITY</th>
	      <th width="15%" align="center">PRICE</th>
	    </tr>
	    <c:forEach items="${shoppingCart.items}" var="item">
	    	<tr class="spaceabove ">
		      <td><a class="simple_anchor" href="removefromcart.do?">remove</a></td>
		      <td><img src="${item.product.image }"></td>
		      <td>${item.product.name }</td>
		      <td align="right">
					<input name="qty" value="${item.quantity}" maxlength="2" size="1">
					<input type="button" class="gradient_button" value="update" onclick="" style="padding:3px">
			  </td>
		      <td align="center"><span class="price">$ ${item.totalPrice}</span></td>
		    </tr>
	    </c:forEach>    
		<tr>
	      <td colspan=4 align="right"><h3>Product Subtotal</h3></td>
	      <td align="center"><span class="price ">$ ${shoppingCart.productSubtotal}</span></td>
	    </tr>
	  </table>
	  <table class="cart_table" width="95%">
		<tr >
	      <td width="100%" colspan=5><h3><span class="marginL">2. Shipping Options</span></h3></td>
	    </tr>
		<tr >
	      <td width="100%" colspan=4><span class="marginL">Select shipping method.</span></td>
	    </tr>
	    <tr>
	      <td ><input type="image" src="http://localhost:8080/ecom4j/images/standard_on.jpg" id="shipping_standard" onclick="switchShipping(this)"/></td>
	      <td width="50%">Delivery in 5-8 business days</td>
	      <td align="center"><span class="price">FREE</span></td>
	    </tr>
		<tr>
	      <td><input type="image" src="http://localhost:8080/ecom4j/images/express_off.jpg" id="shipping_express" onclick="switchShipping(this)"/></td>
	      <td width="50%">Delivery in 3-4 business days</td>
	      <td align="center"><span class="price">$ 19.95</span></td>
	    </tr>
		<tr>
	      <td colspan=2 align="right"><h3>Shipping Subtotal</h3></td>
	      <td align="center" width="15%">
	      	<span class="price" id="shippingSubtotal">
	      		<c:choose>
	      			<c:when test=" ${ shoppingCart.shippingCost gt 0 }">
	      				$ ${shoppingCart.shippingCost }
	      			</c:when>
	      			<c:otherwise>
	      				FREE
	      			</c:otherwise>
	      		</c:choose>
	      		
	      	</span>
	      </td>
	    </tr>
	  </table>
	  <table class="cart_table" width="95%">
		<tr>
	      <td colspan=2 align="right"><h3>Order total</h3></td>
	      <td align="center" width="15%"><span class="price" id="cartTotal">$ ${shoppingCart.total }</span></td>
	    </tr>
	  </table>
	  
    <div style="padding:20px 10px 20px 10px; width:92%">
		<input type="button"  class="gradient_button floatL" value="Continue shopping" onclick="window.location='home.do'" />
		<input type="button"  class="gradient_button_orange floatR" value="Proceed with your order" onclick="" />
	</div>
	  
 </div>