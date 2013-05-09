<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <link rel="stylesheet" href="css/jquery-ui.css" />
  <script src="js/jquery-ui.js"></script>

  <script>
  $(function() {
    $( "#tabs" ).tabs();
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

$( document ).ready(function() {
    $( ".content" ).each(function() {
        var newHeight = 0, $this = $( this );
        $.each( $this.children(), function() {
            newHeight += $( this ).height();
        });
        $this.height( newHeight );
    });
});

$(document).ready(function() {
	$('.jqzoom').jqzoom({
            zoomType: 'standard',
            lens:true,
            preloadImages: false,
            alwaysOn:false
        });
	
});

</script>
<div class="prod_box_list" style="position:width:400px; margin: 25px;overflow:hidden;">
	<div class="prod_image_list" style="float:left;width:100px; margin:25px 10px 0px 25px; text-align:center">
		<input type="image" align="center" src="${remixProduct.image}" style="max-height: 150px"/>
	</div>
	<div class="prod_description_list" style="float:left;width:350px; margin:25px 10px 10px 25px;">
		<div class="strong">${remixProduct.name}</div>
		<div class="">SKU: ${remixProduct.sku}</div>
		<div>
			<span class="stars">${remixProduct.customerReviewAverage}</span>
			<span>
				<fmt:formatNumber var="averageReview" value="${remixProduct.customerReviewAverage}" maxFractionDigits="2" />
					${averageReview} / 5
			</span> 
			<span>(${remixProduct.customerReviewCount} reviews)</span> 
		</div>
		<div style="margin-top:15px">
			${remixProduct.shortDescriptionHtml}
		</div>
	</div>
	<div class="add_to_cart_list" style="float:left; width:150px;height:50px; margin-top:50px;text-align: center">
		<div class="strong price" style="padding:5px;font-size:15px;color:rgb(158, 79, 79)">
			<c:choose>
				<c:when test="${ remixProduct.regularPrice ne remixProduct.salePrice }">
					<span class="text_crossed">$${remixProduct.regularPrice}</span> $${remixProduct.salePrice}
				</c:when>
				<c:otherwise>
					$${remixProduct.salePrice}
				</c:otherwise>
			</c:choose>
		</div>
		<img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'" onclick="addToCart('${remixProduct.productId }')">
	</div>
</div>


<div id="tabs">
  <ul>
    <li><a href="#tabs-1">OVERVIEW</a></li>
    <li><a href="#tabs-2">SPECS</a></li>
    <li><a href="#tabs-3">REVIEWS</a></li>
  </ul>
  <div id="tabs-1">
	${ remixProduct.longDescriptionHtml }
	   <div style="height:20px">&nbsp</div>
       <c:if test="${fn:length(remixProduct.features.feature) gt 0}">
		   <b>Product Features</b>
	   </c:if>
	   <ul>
	    <c:forEach var="feature" items="${ remixProduct.features.feature }">
	    	<% pageContext.setAttribute("newLineChar", "\n"); %>
	    	<c:set var="featureParts" value="${fn:split(feature, newLineChar)}" />
			<c:choose>
				<c:when test="${fn:length(featureParts) gt 1}">
					<li><b>${ featureParts[0] }</b> <br/> ${ featureParts[1] }</li>
				</c:when>
				<c:otherwise>
					<li>${feature }</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</ul>
  </div>
  <div id="tabs-2">
	<table>
           <c:forEach var="spec" items="${remixProduct.details.detail}" varStatus="status">
           	<c:choose>
				<c:when test="${status.count mod 2 eq 0}">
					<tr bgcolor="#CCCCCC">
				</c:when>
				<c:otherwise>
					<tr>
				</c:otherwise>
			</c:choose>
           		<td>
           			${spec.name }
           		</td>
           		<td>
           			${spec.value }
           		</td>
           	</tr>
           </c:forEach>
     </table>
  </div>
  <div id="tabs-3">
    <div style="height:15px">&nbsp</div>
  	<b>Product Reviews</b>
  	<div style="height:20px">&nbsp</div>
  	
  	<c:forEach var="review" items="${reviews}">
        <div style="width:600px">
        	<div><span class="stars">${review.rating}</span> ${review.rating} out of 5.</div>
        	<div><b>${review.title }</b></div>
        	<div style="color:#CCCCCC">${review.submissionTime }</div>
        	<div style="height:15px">&nbsp</div>
        	<div>${review.comment }</div>
        	
        </div>
        <div style="height:30px">&nbsp</div>
        
    </c:forEach>
  </div>
</div>
