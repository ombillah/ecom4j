<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="right_content">
      <div class="shopping_cart">
    <c:choose>
	<c:when test="${session.ShoppingCart == null}" >
	        <div class="cart_title">Shopping cart</div>
        <div class="cart_details"> 0 items <br />
          <span class="border_cart"></span> Total: <span class="price">0$</span> </div>
	</c:when>
	<c:otherwise>
	        <div class="cart_title">Shopping cart</div>
		 <div class="cart_details"> ${session.ShoppingCart.size} items <br />
          <span class="border_cart"></span> Total: <span class="price">${session.ShoppingCart.total } $</span> </div>
	</c:otherwise>
</c:choose>
          
        <div class="cart_icon"><a href="shoppingCart.do" title="header=[Checkout] body=[&nbsp;] fade=[on]"><img src="images/shoppingcart.png" alt="" width="48" height="48" border="0" /></a></div>
      </div>
      <ul class="left_menu">
        <li class="odd"></li>
      </ul>
    </div>