<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="center_content" style="width:780px">
    <c:forEach items="${catalogViewBean.products}" var="product">
		<div class="prod_box">
			<div class="center_prod_box">
				<div class="product_img">
					<input type="image" align="center" src="${product.mainImageUrl}" style="width:130px; height:130px;" />
				</div>
				<div class="product_title">${product.make} ${product.model} </div>
				
				<div class="prod_price">
					<span class="price">$ ${product.unitPrice}</span>
				</div>
			</div>
			<a href="catalog.do"><img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'"></a>
			
		</div>
	</c:forEach>
</div>