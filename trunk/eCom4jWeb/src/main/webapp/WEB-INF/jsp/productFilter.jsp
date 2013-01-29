<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="round_corner light_gray_box catalog_filters" >
	<c:if test="${ param.isParent eq 'true' }">
		<div style="height:20px">&nbsp</div>
		<b>Categories:</b>
		<div style="height:10px">&nbsp</div>
		<input type="checkbox" name="category_all" value="all" checked="checked" />All</br>
		<c:forEach items="${catalogViewBean.categories}" var="category" varStatus="status">
			<c:set var="dots" value="...." />
			<c:choose>
			<c:when test="${fn:length(category.key) lt 18}">
				<c:set var="categoryName" value="${category.key}" />
			</c:when>
			<c:otherwise>
				<c:set var="categoryName" value="${fn:substring(category.key, 0, 18)} ${dots}" />		
			</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${status.count  le 10}">
				<input type="checkbox" name="category_${category.key}" value="${category.key}" />${categoryName} (${category.value})</br>
			</c:when>
			<c:when test="${status.count  eq 11}">
				<span id="more_categories" style="display:none">
					<input type="checkbox" name="category_${category.key}" value="${category.key}" />${categoryName} (${category.value})</br>
			</c:when>
			<c:otherwise>
					<input type="checkbox" name="category_${category.key}" value="${category.key}" />${categoryName} (${category.value})</br>		
			</c:otherwise>
		</c:choose>
		<c:if test="${fn:length(catalogViewBean.categories) gt 10 && status.count eq fn:length(catalogViewBean.categories)}">
				</span>
				<a id="filter_more_categories" href="javascript:void(0)" style="color:#787878;margin-left:20px" onclick="javascript:viewMoreLessFilters('filter_more_categories', 'more_categories')">view all</a>
		</c:if>

		</c:forEach>
	</c:if>
	<div style="height:20px">&nbsp</div>
	<b>Brands:</b>
	<div style="height:10px">&nbsp</div>
	<input type="checkbox" name="make_all" value="all" checked="checked" />All</br>
	<c:forEach items="${catalogViewBean.brands}" var="brand" varStatus="status" >
		<c:set var="dots" value="...." />
		<c:choose>
		<c:when test="${fn:length(brand.key) lt 18}">
			<c:set var="brandName" value="${brand.key}" />
		</c:when>
		<c:otherwise>
			<c:set var="brandName" value="${fn:substring(brand.key, 0, 18)} ${dots}" />		
		</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${status.count  le 10}">
				<input type="checkbox" name="make_${brand.key}" value="${brand.key}" />${brandName} (${brand.value})</br>
			</c:when>
			<c:when test="${status.count  eq 11}">
				<span id="more_brands" style="display:none">
					<input type="checkbox" name="make_${brand.key}" value="${brand.key}" />${brandName} (${brand.value})</br>
			</c:when>
			<c:otherwise>
					<input type="checkbox" name="make_${brand.key}" value="${brand.key}" />${brandName} (${brand.value})</br>		
			</c:otherwise>
		</c:choose>
		<c:if test="${fn:length(catalogViewBean.brands) gt 10 && status.count eq fn:length(catalogViewBean.brands)}">
				</span>
				<a id="filter_more_brands" href="javascript:void(0)" style="color:#787878;margin-left:20px" onclick="javascript:viewMoreLessFilters('filter_more_brands', 'more_brands')">view all</a>
		</c:if>
		
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

function viewMoreLessFilters(currentAnchor, divId) {
	 if($('#' + currentAnchor).text() == 'view all') {
		 $('#'+ divId).css('display', 'block');
		 $('#' + currentAnchor).text('view less');
	 }
	 else {
		 $('#' + divId).css('display', 'none');
		 $('#' + currentAnchor).text('view all');
	 }
	 window.scrollTo(0, 0);
	 return false;
}
</script>
