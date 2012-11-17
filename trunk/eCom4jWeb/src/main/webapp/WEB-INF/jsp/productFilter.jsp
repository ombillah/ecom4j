<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="round_corner gray_box catalog_filters" >
	<div style="height:20px">&nbsp</div>
	<b>Categories:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="category_filter" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.categories}" var="category">
		<c:if test="${ category.value gt 0 }">
			<input type="checkbox" name="category_filter" vlaue="${category.key}" />${category.key} (${category.value})</br>
		</c:if>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
	<b>Brands:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="brand_filter" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.brands}" var="brand">
		<input type="checkbox" name="brand_filter" vlaue="${brand.key}" />${brand.key} (${brand.value})</br>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
	<b>Price Range:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="price_filter" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.priceRanges}" var="range">
		<c:if test="${ range.value gt 0 }">
			<input type="checkbox" name="price_filter" vlaue="${range.key}" />${range.key} (${range.value})</br>
		</c:if>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
</div>