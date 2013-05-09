

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>


function removeFromCart($productId) {
	var height = $(".center_content").css("height");
	$("#ajax_box").css("height", height);
	$("#ajax_box").show();
	$.ajax(
            {
              url:"removefromcart.do", 
              type: "POST",  
              data:  "productId=" + $productId,
              complete: callback, 
            } ); 
	function callback(response) {
		setTimeout(function() {
	    	   $("#ajax_box").hide();
	    	   $('.center_content').replaceWith(response.responseText);
	    	   if (response.responseText.indexOf("Your cart is Empty") >= 0) {
	    		   $('#cart_total_hdr').html("$0.00");
	    	   }
	    	   else {
	    		   $cartTotal = $("#cartTotal").text();
		    	   $('#cart_total_hdr').html($cartTotal);
	    	   }
	    	  
	    	}, 100);
		
	}
}

function updateQty($productId) {
	var height = $(".center_content").css("height");
	$("#ajax_box").css("height", height);
	$("#ajax_box").show();
	$qty = $("#" + $productId + "_qty").val();
	$.ajax(
            {
              url:"updateitemquantity.do", 
              type: "POST",  
              data:  "productId=" + $productId
              +"&quantity=" + $qty,
              complete: callback, 
            } ); 
	function callback(response) {
		setTimeout(function() {
	    	   $("#ajax_box").hide();
	    	   $('.center_content').replaceWith(response.responseText);
	    	   $cartTotal = $("#cartTotal").text();
	    	   $('#cart_total_hdr').html($cartTotal);

	    	}, 100);
		
	}
}

function updateShipping($shippingOption) {
	var height = $(".center_content").css("height");
	$("#ajax_box").css("height", height);
	$("#ajax_box").show();
	$.ajax(
            {
              url:"updateshipping.do", 
              type: "POST",  
              data:  "shippingOption=" + $shippingOption,
              complete: callback, 
            } ); 
	function callback(response) {
		setTimeout(function() {
	    	   $("#ajax_box").hide();
	    	   $('.center_content').replaceWith(response.responseText);
	    	   $cartTotal = $("#cartTotal").text();
	    	   $('#cart_total_hdr').html($cartTotal);
	    	}, 100);
		
	}
}
</script>

<div class="center_content" style="padding-left:100px">
	<div id="ajax_box" class="ajax_box" style="display:none">
		<div class="ajax_box" style="background: url(images/ajax-loader.gif) no-repeat center center; "></div>
	</div>
    
    <div style="padding:50px 10px 20px 10px; width:92%">
		<input type="button"  class="gradient_button floatL" value="Continue shopping" onclick="window.location='home.do'" />
		<c:if test="${fn:length(shoppingCart.items) gt 0}">
			<input type="button"  class="gradient_button_orange floatR" value="Proceed with your order" onclick="window.location='checkout-payment.do'" />		
		</c:if>
	</div>
    
	<c:choose>
		<c:when test="${fn:length(shoppingCart.items) eq 0}">
			<table class="cart_table" width="95%" height="200px">
				<tr align="center" >
			      <td width="100%" colspan=5><h3><span class="marginL">Your cart is Empty!</span></h3></td>
			    </tr>
			   
			  </table>
		</c:when>
		<c:otherwise>
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
				      <td><a class="simple_anchor" href="javascript:removeFromCart('${item.product.productId}')">remove</a></td>
				      <td><img src="${item.product.image }"></td>
				      <td>${item.product.name }</td>
				      <td align="right">
							<input name="${item.product.productId}_qty" id="${item.product.productId}_qty" value="${item.quantity}" maxlength="2" size="1">
							<input type="button" class="gradient_button" value="update" onclick="updateQty('${item.product.productId}')" style="padding:3px">
					  </td>
				      <td align="center">
				      	<span class="price">
				      	$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${ item.totalPrice }" />
				      	</span>
				      </td>
				    </tr>
			    </c:forEach>    
				<tr>
			      <td colspan=4 align="right"><h3>Product Subtotal</h3></td>
			      <td align="center">
			      	<span class="price ">
			      		$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${shoppingCart.productSubtotal }" />
			      	</span>
			      </td>
			    </tr>
			  </table>
			  <c:choose>
			      	<c:when test="${ shoppingCart.shippingCost gt 0 }">
			      		<c:set var="standardImage" value="images/standard_off.jpg" />
			      		<c:set var="expressImage" value="images/express_on.jpg" />
			      	</c:when>
			      	<c:otherwise>
			      		<c:set var="standardImage" value="images/standard_on.jpg" />
			      		<c:set var="expressImage" value="images/express_off.jpg" />
			      	</c:otherwise>
			   </c:choose>
			  <table class="cart_table" width="95%">
				<tr >
			      <td width="100%" colspan=5><h3><span class="marginL">2. Shipping Options</span></h3></td>
			    </tr>
				<tr >
			      <td width="100%" colspan=4><span class="marginL">Select shipping method.</span></td>
			    </tr>
			    <tr>
			      <td ><input type="image" src="${standardImage }" id="shipping_standard" onclick="updateShipping('standard')"/></td>
			      <td width="50%">Delivery in 5-8 business days</td>
			      <td align="center"><span class="price">FREE</span></td>
			    </tr>
				<tr>
			      <td><input type="image" src="${expressImage}" id="shipping_express" onclick="updateShipping('express')"/></td>
			      <td width="50%">Delivery in 3-4 business days</td>
			      <td align="center"><span class="price">$ 19.95</span></td>
			    </tr>
				<tr>
			      <td colspan=2 align="right"><h3>Shipping Subtotal</h3></td>
			      <td align="center" width="15%">
			      	<span class="price" id="shippingSubtotal">
			      		<c:choose>
			      			<c:when test="${shoppingCart.shippingCost gt 0 }">
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
			      <td align="center" width="15%">
			      	<span class="price" id="cartTotal">
			      		$ <fmt:formatNumber type="number" maxFractionDigits="3" value="${shoppingCart.total }" />
			      	</span>
			      </td>
			    </tr>
			  </table>
			  
		    <div style="padding:20px 10px 20px 10px; width:92%">
				<input type="button"  class="gradient_button floatL" value="Continue shopping" onclick="window.location='home.do'" />
				<input type="button"  class="gradient_button_orange floatR" value="Proceed with your order" onclick="window.location='checkout-payment.do'" />
			</div>
		</c:otherwise>
	</c:choose>
	
 </div>