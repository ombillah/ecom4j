<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/tld/utils.tld" prefix="util" %>

<div class="left_content">
	<div class="title_box">Categories</div>
	<ul class="left_menu">
		<c:forEach items="${productCategories}" var="category" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 eq 0}">
					<li class="even"><a href="catalog.do?category=${util:encode(category.key)}">${category.key}</a></li>
				</c:when>
				<c:otherwise>
					<li class="odd"><a href="catalog.do?category=${util:encode(category.key)}">${category.key}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	<div style="height:20px">&nbsp</div>
	<div class="title_box">Brands</div>
	<ul class="left_menu">
		<c:forEach items="${brands}" var="brand" varStatus="status">
			<c:choose>
				<c:when test="${status.count % 2 eq 0}">
					<li class="even"><a href="catalog.do?brand=${brand.key}">${brand.key}</a></li>
				</c:when>
				<c:otherwise>
					<li class="odd"><a href="catalog.do?brand=${brand.key}">${brand.key}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>