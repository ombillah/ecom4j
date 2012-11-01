
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="center_content">
	<form action="addToCart.do" method="POST">
		<input type="hidden" name="productID" value="${product.productID}" />
		<div class="center_title_bar">
			<c:out value="${product.productID}" />
		</div>
		<div class="prod_box_big">
			<div class="center_prod_box_big">
				<div class="product_img_big">
					<img src="${product.src}" alt="Product" border="0" width="130"
						height="130" />
				</div>
				<div class="details_big_box">
					<div class="product_title_big">
						<c:out value="${product.productID}" />
					</div>
					<div class="specifications">
						<span style="font-size: 10px"><c:out
								value="${product.description}" /></span><br /> Avalaibility: <span
							class="blue"><c:out value="${product.status}" /></span><br />
						Quantity: <span class="blue"><c:out
								value="${product.quantity}" /></span><br />
					</div>
					<div class="prod_price_big">
						<span class="price"><c:out value="${product.unitPrice}" />
							$</span>
					</div>
					<div>
						<input type="image"
							src="images/addtocart.jpg"
							align="left" cssStyle="height:25px; width:60px" />
					</div>
				</div>
			</div>
		</div>
		</s:form>
</div>
