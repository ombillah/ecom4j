<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
</script>
<div class="center_content">
	<div id="slides">
		<div class="slides_container">
			<a href="http://www.flickr.com/photos/jliba/4665625073/" title="145.365 - Happy Bokeh Thursday! | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/desktops.jpg" width="570" height="270" alt="Slide 1"></a>
			<a href="http://www.flickr.com/photos/stephangeyer/3020487807/" title="Taxi | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/slide-2.jpg" width="570" height="270" alt="Slide 2"></a>
			<a href="http://www.flickr.com/photos/childofwar/2984345060/" title="Happy Bokeh raining Day | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/slide-3.jpg" width="570" height="270" alt="Slide 3"></a>
			<a href="http://www.flickr.com/photos/b-tal/117037943/" title="We Eat Light | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/slide-4.jpg" width="570" height="270" alt="Slide 4"></a>
			<a href="http://www.flickr.com/photos/bu7amd/3447416780/" title="I must go down to the sea again, to the lonely sea and the sky; and all I ask is a tall ship and a star to steer her by. | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/slide-5.jpg" width="570" height="270" alt="Slide 5"></a>
			<a href="http://www.flickr.com/photos/streetpreacher/2078765853/" title="twelve.inch | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/slide-6.jpg" width="570" height="270" alt="Slide 6"></a>
			<a href="http://www.flickr.com/photos/aftab/3152515428/" title="Save my love for loneliness | Flickr - Photo Sharing!" target="_blank"><img src="images/slides/slide-7.jpg" width="570" height="270" alt="Slide 7"></a>
		</div>
		<a href="#" class="prev"><img src="images/slides/arrow-prev.png" width="24" height="43" alt="Arrow Prev"></a>
		<a href="#" class="next"><img src="images/slides/arrow-next.png" width="24" height="43" alt="Arrow Next"></a>
	</div>
		
	<c:forEach items="${featuredProducts}" var="product">
		<div class="prod_box">
			<div class="center_prod_box">
				<div class="product_img">
					<input type="image" align="center" src="${product.mainImageUrl}" style="width:130px; height:130px;" />
				</div>
				<div class="product_title">${product.name}</div>
				
				<div class="prod_price">
					<span class="price">$ ${product.salePrice}</span>
				</div>
			</div>
			<a href="catalog.do"><img alt="" src="images/addtocart.png" onmouseover="this.src='images/addtocart_hover.png'" onmouseout="this.src='images/addtocart.png'"></a>
			
		</div>
	</c:forEach>
</div>