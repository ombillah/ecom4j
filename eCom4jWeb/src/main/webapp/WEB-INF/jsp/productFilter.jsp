<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="round_corner gray_box catalog_filters" >
	<div style="height:20px">&nbsp</div>
	<b>Categories:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="category_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.categories}" var="category">
		<c:if test="${ category.value gt 0 }">
			<input type="checkbox" name="category_${category.key}" vlaue="${category.key}" />${category.key} (${category.value})</br>
		</c:if>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
	<b>Brands:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="brand_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.brands}" var="brand">
		<input type="checkbox" name="brand_${brand.key}" vlaue="${brand.key}" />${brand.key} (${brand.value})</br>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
	<b>Price Range:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="price_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.priceRanges}" var="range">
		<c:if test="${ range.value gt 0 }">
			<input type="checkbox" name="price_${range.key}" vlaue="${range.key}" />${range.key} (${range.value})</br>
		</c:if>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
</div>


<script>

setProductFilter("category");
setProductFilter("brand");
setProductFilter("price");

function setProductFilter(filterName) {
	var $filter = $('input[name*="' + filterName + '"]');

	$filter.click(function() {
	   if(this.value == 'all'){
			 $('input[name*="' + filterName + '"]').each(function() {
				$(this).attr('checked',false);
			 });
			 $(this).attr('checked', true);
		}
   		else {
           $('input[name=' + filterName + '_all]').attr('checked', false);
       	}
	   var $checked = $('input[name*="' + filterName + '"]:checked');
	   alert($checked.length);
	});

}


</script>
