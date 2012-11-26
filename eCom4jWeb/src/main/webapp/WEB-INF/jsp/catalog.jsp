<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="center_content" style="width:780px">
	<div id="ajax_box" class="ajax_box" style="display:none">
		<div class="ajax_box" style="background: url(images/ajax-loader.gif) no-repeat center center; "></div>
	</div>
	<div class="pagination_box light_gray_box marginT">
		<div class="floatL">
			items 1 to 9 of 29 total
		</div>
		<div class="floatL" style="margin-left:150px">
			1 2 3 4 
		</div>
		<div class="floatR">
			show <select id="pageSize" name="pageSize" style="background:#F0F0F0;border:1px solid #e8e8e8">
				  <option value="10">10</option>
			    </select> per page
		</div>
	</div>
	<div class="pagination_box light_gray_box marginT">
		<div class="floatL">
			View as: <span class="marginL">Grid</span>  <span class="marginL">List</span>
		</div>
		<div class="floatR">
			Sort By <select id="sortOption" name="sortOption" style="background:#F0F0F0;border:1px solid #e8e8e8">
				  <option value="Name">Name</option>
			    </select> <img src="images/ascending.png"></img>
		</div>
	</div>
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