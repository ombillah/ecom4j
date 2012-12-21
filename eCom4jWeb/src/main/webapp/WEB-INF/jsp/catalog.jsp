<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>

$(document).ready(function(){
   setPaginationEvent();
   setPageSizeChangeEvent();
 });
 
$.fn.stars = function() {
    return $(this).each(function() {
        
        var val = parseFloat($(this).html());
        val = Math.round(val * 2) / 2; // round to nearest half
        // Make sure that the value is in 0 - 5 range, multiply to get width
        var size = Math.max(0, (Math.min(5, val))) * 16;
        // Create stars holder
        var $span = $('<span />').width(size);
        // Replace the numerical value with stars
        $(this).html($span);
    });
}

$(function() {
    $('span.stars').stars();
});
function setPaginationEvent() {
	$("#pagination a").live('click',function () {
		var height = $(".center_content").css("height");
		$("#ajax_box").css("position", 'absolute');
		$("#ajax_box").show();
		
		var $pageNumber = this.text;
		if (this.id == "previous" ) {
			$pageNumber = parseInt($("#currentPageNumber").val()) - 1;
		}
		else if (this.id == "next") {
			$pageNumber = parseInt($("#currentPageNumber").val()) + 1;
		}

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
			<c:if test="${ catalogViewBean.currentPage.currentPageNumber ne 1  }" >
				<a href="#" id="previous"><img alt="" src="images/previous.png"></a>				
			</c:if>
			<input type="hidden" id="currentPageNumber" value="${ catalogViewBean.currentPage.currentPageNumber }" />
			
			<c:choose>
				<c:when test="${ 1 eq catalogViewBean.currentPage.currentPageNumber }">
					<span style="background-color:#ffffff;padding:5px 5px 5px 5px;border: 1px solid #e8e8e8;">1</span>
				</c:when>
				<c:otherwise>
					<a href="#" id="page_1" style="color:#666; text-decoration: none">1</a>
				</c:otherwise>
			</c:choose>
			
			<c:if test="${ catalogViewBean.currentPage.currentPageNumber > 6 }" >
				....
			</c:if>
			<c:forEach var="i" begin="2" end="${ catalogViewBean.currentPage.totalNumberOfPages }" step="1">
				<c:if test="${ (i > catalogViewBean.currentPage.currentPageNumber - 4) and (i < catalogViewBean.currentPage.currentPageNumber + 4) and ( i ne catalogViewBean.currentPage.totalNumberOfPages)  }" >
					<c:choose>
						<c:when test="${ i eq catalogViewBean.currentPage.currentPageNumber }">
							<span style="background-color:#ffffff;padding:5px 5px 5px 5px;border: 1px solid #e8e8e8;"><c:out value="${i}" /></span>
						</c:when>
						<c:otherwise>
							<a href="#" id="page_${i}" style="color:#666; text-decoration: none"><c:out value="${i}" /></a>
						</c:otherwise>
					</c:choose>
				</c:if> 
			</c:forEach>

			<c:if test="${ catalogViewBean.currentPage.currentPageNumber < catalogViewBean.currentPage.totalNumberOfPages - 6 }" >
				....
			</c:if>
			
			<c:choose>
				<c:when test="${ catalogViewBean.currentPage.totalNumberOfPages eq catalogViewBean.currentPage.currentPageNumber }">
					<span style="background-color:#ffffff;padding:5px 5px 5px 5px;border: 1px solid #e8e8e8;">${catalogViewBean.currentPage.totalNumberOfPages}</span>
				</c:when>
				<c:otherwise>
					<a href="#" id="page_${catalogViewBean.currentPage.totalNumberOfPages }" style="color:#666; text-decoration: none">${catalogViewBean.currentPage.totalNumberOfPages}</a>
				</c:otherwise>
			</c:choose>
			
			
			<c:if test="${catalogViewBean.currentPage.currentPageNumber ne catalogViewBean.currentPage.totalNumberOfPages }" >
				<a href="#" id="next"><img alt="" src="images/next.png"></a>
			</c:if>
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
	<div id="grid_view">
	    <c:forEach items="${catalogViewBean.currentPage.products}" var="product" >
			<div class="prod_box">
				<div class="center_prod_box">
					<div style="text-align: center;padding-left:45px">
						<span class="stars">${product.productRatingAverage}</span>
					</div>
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
</div>