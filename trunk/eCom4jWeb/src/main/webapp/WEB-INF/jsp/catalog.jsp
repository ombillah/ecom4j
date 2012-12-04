<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>

$(document).ready(function(){
   setPaginationEvent();
   setPageSizeChangeEvent();
 });
 
function setPaginationEvent() {
	$("#pagination a").live('click',function () {
		var height = $(".center_content").css("height");
		$("#ajax_box").css("height", height);
		$("#ajax_box").show();
	    var $pageNumber = this.text;
	    $.ajax(
	            {
	              url:"doPagination.do", 
	              type: "POST",  
	              data:  "pageNumber=" + $pageNumber,
	              complete: callback, 
	            } ); 
		function callback(response) {
			setTimeout(function() {
		    	   $("#ajax_box").hide();
		    	   $('.center_content').replaceWith(response.responseText);
		    	}, 500);
			
		}
	});
}

function setPageSizeChangeEvent() {
	$('#pageSize').bind('change', function(ev) {

		var height = $(".center_content").css("height");
		$("#ajax_box").css("height", height);
		$("#ajax_box").show();
	    var $pageSize = $(this).val();
	    $.ajax(
	            {
	              url:"changePageSize.do", 
	              type: "POST",  
	              data:  "pageSize=" + $pageSize,
	              complete: callback, 
	            } ); 
		function callback(response) {
			setTimeout(function() {
		    	   $("#ajax_box").hide();
		    	   $('.center_content').replaceWith(response.responseText);
		    	}, 500);
			
		}

	 });
}


</script>

<div class="center_content" style="width:780px">
	<div id="ajax_box" class="ajax_box" style="display:none">
		<div class="ajax_box" style="background: url(images/ajax-loader.gif) no-repeat center center; "></div>
	</div>
	<div id="pagination" class="pagination_box light_gray_box marginT">
		<div class="floatL">
			items ${ catalogViewBean.currentPage.fistItemsIndex } to ${ catalogViewBean.currentPage.lastItemsIndex } of ${ catalogViewBean.currentPage.totalNumberOfProducts }  total
		</div>
		<div class="floatL" style="margin-left:150px">
			<c:forEach var="i" begin="1" end="${ catalogViewBean.currentPage.totalNumberOfPages }" step="1">
				<c:choose>
					<c:when test="${ i eq catalogViewBean.currentPage.currentPageNumber}">
						<b><c:out value="${i}" /></b>
					</c:when>
					<c:otherwise>
						<a href="#" id="page_${i}" style="color:#262626;"><c:out value="${i}" /></a>
					</c:otherwise>
				</c:choose> 
				
			</c:forEach>
		</div>
		<div class="floatR">
			show <select id="pageSize" name="pageSize" style="background:#F0F0F0;border:1px solid #e8e8e8">
					<c:forEach var="size" items="${ catalogViewBean.currentPage.availablePageSizes }">
						<c:choose>
							<c:when test="${ size eq catalogViewBean.currentPage.pageSize}">
								<option value="${size}" selected="selected">${size}</option>
							</c:when>
							<c:otherwise>
								<option value="${size}">${size}</option>
							</c:otherwise>
						</c:choose> 
					</c:forEach>
			     </select> per page
		</div>
	</div>
	<div class="pagination_box light_gray_box marginT">
		<div class="floatL">
			View as: <span class="marginL">Grid</span>  <span class="marginL">List</span>
		</div>
		<div class="floatR">
			Sort By <select id="sortOption" name="sortOption" style="background:#F0F0F0;border:1px solid #e8e8e8">
				  <option value="">Highest Rated</option>
				  <option value="">Most Reviewed</option>
				  <option value="price_asc">Lowest Price</option>
				  <option value="price_desc">Highest Price</option>
				  <option value="createdDate_desc">Recently Added</option>
			    </select>
		</div>
	</div>
    <c:forEach items="${catalogViewBean.currentPage.products}" var="product">
		<div class="prod_box">
			<div class="center_prod_box">
				<div class="product_img">
					<input type="image" align="center" src="${product.mainImageUrl}" style="width:130px; height:130px;" />
				</div>
				<div class="product_title">${product.name}</div>
				
				<div class="prod_price">
					<span class="price">$ ${product.unitPrice}</span>
				</div>
			</div>
			<a href="catalog.do"><img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'"></a>
		</div>
	</c:forEach>
</div>