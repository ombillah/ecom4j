<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>

$(document).ready(function(){
   setPaginationEvent();
   setPageSizeChangeEvent();
   setSortingEvent();
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
		
		var $listViewDisplay = $("#list_view").css("display");
		var $gridViewDisplay = $("#grid_view").css("display");
		var $listaDisplay = $("#list_a").css("text-decoration");
		var $gridaDisplay = $("#grid_a").css("text-decoration");

		var $seletedOption = $('#sortOption').val();
		var $sortValue = $seletedOption.split("_");
		var $sortField = $sortValue[0];
		var $sortOrder = $sortValue[1];
		
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
		    	   $("#grid_view").css("display", $gridViewDisplay);
					$("#list_view").css("display", $listViewDisplay);
					$("#list_a").css("text-decoration", $listaDisplay);
					$("#grid_a").css("text-decoration", $gridaDisplay);
					$("#sortOption").val($seletedOption);
		    	}, 500);
			
		}
	});
}

function setSortingEvent() {
	$('#sortOption').bind('change', function(ev) {

		var height = $(".center_content").css("height");
		$("#ajax_box").css("height", height);
		$("#ajax_box").show();
		
		var $listViewDisplay = $("#list_view").css("display");
		var $gridViewDisplay = $("#grid_view").css("display");
		var $listaDisplay = $("#list_a").css("text-decoration");
		var $gridaDisplay = $("#grid_a").css("text-decoration");

		var $seletedOption = $(this).val();
	    var $sortValue = $seletedOption.split("_");
	    var $sortField = $sortValue[0];
	    var $sortOrder = $sortValue[1];
	    
	    $.ajax(
	            {
	              url:"sortResults.do", 
	              type: "POST",  
	              data:  "sortField=" + $sortField + "&sortOrder=" + $sortOrder,
	              complete: callback, 
	            } ); 
		function callback(response) {
			setTimeout(function() {
		    	   $("#ajax_box").hide();
		    	   $('.center_content').replaceWith(response.responseText);
		    	   $("#grid_view").css("display", $gridViewDisplay);
					$("#list_view").css("display", $listViewDisplay);
					$("#list_a").css("text-decoration", $listaDisplay);
					$("#grid_a").css("text-decoration", $gridaDisplay);
					$("#sortOption").val($seletedOption);
		    	}, 500);
			
		}

	 });
}

function setPageSizeChangeEvent() {
	$('#pageSize').bind('change', function(ev) {

		var height = $(".center_content").css("height");
		$("#ajax_box").css("height", height);
		$("#ajax_box").show();
		
		var $listViewDisplay = $("#list_view").css("display");
		var $gridViewDisplay = $("#grid_view").css("display");
		var $listaDisplay = $("#list_a").css("text-decoration");
		var $gridaDisplay = $("#grid_a").css("text-decoration");

		var $seletedOption = $('#sortOption').val();
		var $sortValue = $seletedOption.split("_");
		var $sortField = $sortValue[0];
		var $sortOrder = $sortValue[1];
		
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
		    	   $("#grid_view").css("display", $gridViewDisplay);
					$("#list_view").css("display", $listViewDisplay);
					$("#list_a").css("text-decoration", $listaDisplay);
					$("#grid_a").css("text-decoration", $gridaDisplay);
					$("#sortOption").val($seletedOption);
		    	}, 500);
			
		}

	 });
}

function switchView() {
	var listViewDisplay = $("#list_view").css("display");
	if(listViewDisplay == 'none') {
		$("#grid_view").css("display", 'none');
		$("#list_view").css("display", 'block');
		$("#list_a").css("text-decoration", 'none');
		$("#grid_a").css("text-decoration", 'underline');
	} else {
		$("#list_view").css("display", 'none');
		$("#grid_view").css("display", 'block');
		$("#list_a").css("text-decoration", 'underline');
		$("#grid_a").css("text-decoration", 'none');
	}
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
			View as: <span class="marginL"><a id="grid_a" class="simple_anchor" href="javascript:switchView()" style="text-decoration:none">Grid</a></span>  <span class="marginL"><a id="list_a" class="simple_anchor" href="javascript:switchView()">List</a></span>
		</div>
		<div class="floatR">
			Sort By <select id="sortOption" name="sortOption" style="background:#F0F0F0;border:1px solid #e8e8e8">
				  <option value="customerReviewAverage_desc">Highest Rated</option>
				  <option value="customerReviewCount_desc">Most Reviewed</option>
				  <option value="salePrice_asc">Lowest Price</option>
				  <option value="salePrice_desc">Highest Price</option>
				  <option value="createdDate_desc">Recently Added</option>
			    </select>
		</div>
	</div>
	<div id="grid_view">`
	    <c:forEach items="${catalogViewBean.currentPage.products}" var="product" >
			<div class="prod_box">
				<div class="center_prod_box">
					<div style="text-align: center;padding-left:45px">
						<span class="stars">${product.customerReviewAverage}</span>
					</div>
					<div class="product_img">
						<a href="productDetails.do?productId=${product.productId }">
							<img align="center" src="${product.image}" style="max-height: 150px"/>
						</a>
					</div>
					<div class="product_title">
						<c:set var="dots" value="...." />
						<c:choose>
							<c:when test="${fn:length(product.name) lt 50}">
								<c:set var="productName" value="${product.name}" />
							</c:when>
							<c:otherwise>
								<c:set var="productName" value="${fn:substring(product.name, 0, 50)} ${dots}" />		
							</c:otherwise>
						</c:choose>
						<a href="productDetails.do?productId=${product.productId }" style="color:#787878">${productName}</a>
					</div>
					
					<div class="prod_price">
						<span class="price">$ ${product.salePrice}</span>
					</div>
				</div>
				<img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'" onclick="addToCart('${product.productId }')">
			</div>
		</c:forEach>
	</div>
	<div id="list_view" style="display:none">
	    <c:forEach items="${catalogViewBean.currentPage.products}" var="product" >
			<div class="prod_box_list" style="position:width:500px; margin: 25px;border:1px solid;overflow:hidden;">
				<div class="prod_image_list" style="float:left;width:100px; margin:25px 10px 0px 25px; text-align:center">
					<a href="productDetails.do?productId=${product.productId }">
						<img align="center" src="${product.image}" style="max-height: 150px"/>
					</a>
				</div>
				<div class="prod_description_list" style="float:left;width:350px; margin:25px 10px 10px 25px;">
					<div class="strong">${product.name}</div>
					<div class="">SKU: ${product.sku}</div>
					<div>
						<span class="stars">${product.customerReviewAverage}</span>
						<span>
							<fmt:formatNumber var="averageReview" value="${product.customerReviewAverage}" maxFractionDigits="2" />
								${averageReview} / 5
						</span> 
						<span>(${product.customerReviewCount} reviews)</span> 
					</div>
					<div style="margin-top:15px">
						${product.shortDescriptionHtml}
					</div>
				</div>
				<div class="add_to_cart_list" style="float:right; width:150px;height:50px; margin-top:50px;text-align: center">
					<div class="strong price" style="padding:5px;font-size:15px;color:rgb(158, 79, 79)">
						<c:choose>
							<c:when test="${ product.regularPrice ne product.salePrice }">
								<span class="text_crossed">$${product.regularPrice}</span> $${product.salePrice}
							</c:when>
							<c:otherwise>
								$${product.salePrice}
							</c:otherwise>
						</c:choose>
					</div>
					<img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'" onclick="addToCart('${product.productId }')">
				</div>
			</div>
		</c:forEach>
	</div>
</div>