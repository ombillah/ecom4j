<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="center_content">
		<div class="main_product" style="position:relative; margin:5px 5px 5px 5px; width:100%; height:250px;border: 1px solid">
			<div class="main_product_img" style="position:relative; float:left;margin:5px 5px 5px 5px; width:200px; height:200px;border: 1px solid">
				<img alt="" src="${mainProduct.imageUrl }">
			</div>
			<div class="main_product_desc" style="position:relative; float:left;margin:15px 5px 5px 5px; width:400px; height:200px;border: 1px solid">
				<span>NEW!</span><span>${mainProduct.make } ${mainProduct.model }</span>
			</div>
		</div>
	
	<div class="center_title_bar">Latest Products</div>

	<c:forEach items="${latest}" var="product">
		<div class="prod_box">
			<div class="top_prod_box"></div>
			<div class="center_prod_box">
				<form action="details.do" method="GET">
					<input type="hidden" name="productID" value="${product.productID}" />
					<div class="product_title">${product.productID}</div>
					<div class="product_img">
						<input type="image" align="center" src="${product.imageUrl}" style="width:130px; height:130px;" />
					</div>
				</form>

				<div class="prod_price">
					<span class="price">$ ${product.unitPrice}</span>
				</div>
			</div>
			<div class="prod_details_tab">
				<input type="hidden" name="productID" value="${product.productID}" />
				<a href="details.do?productID=${product.productID}" class="prod_details">details</a>
			</div>
		</div>
	</c:forEach>
</div>