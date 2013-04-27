<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
	$(function(){
		$('#slides').slides({
			preload: true,
			preloadImage: 'images/slides/loading.gif',
			play: 5000,
			pause: 2500,
			hoverPause: true
		});
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
</script>
<div class="center_content">
	<div id="slides">
		<div class="slides_container">
			<a href="catalog.do?category=abcat0502000&manufacturer=Apple®" title="" ><img src="images/slides/macbook.jpg" width="570" height="270" alt="Slide 1"></a>
			<a href="catalog.do?category=abcat0501000&manufacturer=Apple®" title="" ><img src="images/slides/macpro.jpg" width="570" height="270" alt="Slide 2"></a>
			<a href="catalog.do?category=abcat0502000&manufacturer=Lenovo" title="" ><img src="images/slides/lenovo.jpg" width="570" height="270" alt="Slide 3"></a>
			<a href="catalog.do?category=pcmcat209400050001&manufacturer=Apple®" title="" ><img src="images/slides/iphone.jpg" width="570" height="270" alt="Slide 4"></a>
		</div>
		<a href="#" class="prev"><img src="images/slides/arrow-prev.png" width="24" height="43" alt="Arrow Prev"></a>
		<a href="#" class="next"><img src="images/slides/arrow-next.png" width="24" height="43" alt="Arrow Next"></a>
	</div>
	
	<div style="margin:75px 0px 0px -10px">
	<div class="register_hdr_block">Featured Products</div>
	 <div style="height:20px">&nbsp</div>
	    <c:forEach items="${featuredProducts}" var="product" >
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
				<a href="catalog.do"><img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'"></a>
			</div>
		</c:forEach>
	</div>
</div>