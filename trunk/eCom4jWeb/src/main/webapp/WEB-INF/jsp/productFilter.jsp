<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="round_corner light_gray_box catalog_filters" >
	<div style="height:20px">&nbsp</div>
	<b>Categories:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="category_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.categories}" var="category">
		<c:if test="${ category.value gt 0 }">
			<input type="checkbox" name="category_${category.key}" value="${category.key}" />${category.key} (${category.value})</br>
		</c:if>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
	<b>Brands:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="make_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.brands}" var="brand">
		<input type="checkbox" name="make_${brand.key}" value="${brand.key}" />${brand.key} (${brand.value})</br>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
	<b>Price Range:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="price_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.priceRanges}" var="range">
		<c:if test="${ range.value gt 0 }">
			<c:set var="value" value="${fn:replace(range.key,  '$', '')}" />
			<c:set var="value" value="${fn:replace(value,  ' and up', ' - 9999999999')}" />
			<input type="checkbox" name="price_${range.key}" value="${value}" />${range.key} (${range.value})</br>
		</c:if>
	</c:forEach>
	<div style="height:20px">&nbsp</div>
</div>


<script>

setProductFilter("category");
setProductFilter("make");
setProductFilter("price");

function setProductFilter(filterName) {
	var $filter = $('input[name*="' + filterName + '"]');

	$filter.click(function() {
		var height = $(".center_content").css("height");
		$("#ajax_box").css("height", height);
		$("#ajax_box").show();
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
	   if($checked.length == 0) {
		   $('input[name=' + filterName + '_all]').attr('checked', true);
		   $checked = $('input[name*="' + filterName + '"]:checked');
	   }
	   var filterValues = []
	   $.each($checked , function(index, field) { 
		   filterValues.push(field.value);
		 });
	   
	   var catalogFilter = new Object();
	   catalogFilter.name = filterName;
	   catalogFilter.values = filterValues;
	   var jsonString = JSON.stringify( catalogFilter );
	   
	   $.ajax(
            {
              url:"addfilter.do", 
              type: "POST",  
              contentType: "application/json; charset=utf-8",  
              data:  jsonString,
              complete: callback, 
            } ); 
	});
	function callback(response) {
		setTimeout(function() {
	    	   $("#ajax_box").hide();
	    	   $('.center_content').replaceWith(response.responseText);
	    	}, 500);
		
	}
}


</script>
