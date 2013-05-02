<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tld/utils.tld" prefix="util"%>

<script type="text/javascript">
	$(function() {
		if ($.browser.msie && $.browser.version.substr(0, 1) < 7) {
			$('li').has('ul').mouseover(function() {
				$(this).children('ul').show();
			}).mouseout(function() {
				$(this).children('ul').hide();
			})
		}
	});
	
	function addToCart($productId){
		$('#productId').val($productId);
		$('#cartform').submit();
	}
</script>

<form id="cartform" action="addtocart.do" method="post">
	<input type="hidden" name="productId" id="productId" />
</form>
<div id="header">
	<div id="logo">
		<a href="home.do"><img border="0" src="images/logo.png" alt=""></a>
	</div>
	<div class="header_box_right">
		<div class="cart_button round_corner">
	      <a href="#">Shopping Cart</a>
		</div>
		<div class="login_header">
				<span class="marginR"><a href="login.do">6 items</a></span> 
				<font color="gray">|</font> 
				<span class="marginL">$6000.00</span>
		</div>
		<sec:authorize access="isAnonymous()">
			<div class="login_header">
				<span class="marginR"><a href="login.do">Sign in</a></span> <font
					color="gray">|</font> <span class="marginL"><a
					href="register.do">Create account</a></span>
			</div>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<div class="login_header">
				<div>
					Welcome <b><sec:authentication property="principal.customer.firstName" /></b>
				</div>
				<span class="marginR"><a href="myaccount.do">My Account</a></span> 
				<font color="gray">|</font> 
				<span class="marginL"><a href="j_spring_security_logout">Sign out</a></span>
			</div>
		</sec:authorize>
		
	</div>
	
</div>
<ul id="horizontal_nav">

	<li><a href="home.do"><b>Home Page</b></a></li>

	<li><a href="#" class="drop"><b>Products</b></a>
	<!-- Begin Products Dropdown -->

		<div class="dropdown_4columns">
			<!-- Begin 4 columns container -->
			<c:forEach items="${productCategories}" var="category">
				<div class="col_1">
					<h3>
						<a href="catalog.do?category=${util:encode(category.categoryId)}&isParent=true"><b>${category.categoryName}</b></a>
					</h3>
					<ul>
						<c:forEach items="${category.subCategories}" var="subCategory" varStatus="status">
							<c:if test="${status.count  le 8}">
								<li><a class="normal_weight" href="catalog.do?category=${util:encode(subCategory.categoryId)}">${subCategory.categoryName}</a></li>
							</c:if>
							<c:if test="${status.count  eq 9}">
								<li><a class="normal_weight" href="catalog.do?category=${util:encode(category.categoryId)}&isParent=true"><b>View All</b></a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</c:forEach>
		</div>
		<!-- End 4 columns container -->
	</li>
	<!-- End 4 columns Item -->
	<li><a href="#"><b>Contact Us</b></a></li>
	<form action="catalogsearch.do" >
	<li class="no_hover" style="margin-right:1px">
			<div class="search_box round_corner">
		      <input name="keyword" style="border: none;"/>
			</div>
	</li>
	<li>
		<select name="category" style="background:#F0F0F0;border:1px solid #e8e8e8;color:#666666" >
			  <option  value="">Select a Category</option>
			  <c:forEach items="${productCategories}" var="category">
			  	<option  value="${util:encode(category.categoryId)}">${category.categoryName}</option>
			   </c:forEach>
		</select>
	    <input type="image" alt="" src="images/search-action.jpg" />
		
	</li>
	</form>
	
	
</ul>
