<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
  <div id="header">
    <div id="logo"><a href="home.do"><img border="0" src="images/logo.png" alt=""></a></div>
    <div class="top_search">
      <div class="search_text"><a href="search.do">Advanced Search</a></div>
      <form action="search.do" method="GET">
	      <input class="search_input" name="keyword">
	      <input type="image" src="images/search.gif" class="search_bt">
      </form>
    </div>
    <sec:authorize access="isAnonymous()">
		<div class="login_header round_corner">
	      <span class="marginR"><a href="login.do">Sign in</a></span> <font color="red">|</font> <span class="marginL"><a href="register.do">Create account</a></span>
		</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
	  	<div class="login_header">
		  <div>Welcome <b>Oussama</b></div>
	      <span class="marginR"><a href="myaccount.do">My Account</a></span> <font color="red">|</font> <span class="marginL"><a href="j_spring_security_logout">Sign out</a></span>
		</div>
	</sec:authorize>
  </div>
	
  <div class='cssmenu'>
	<ul>
	   <li><a href='home.do'><span>Home</span></a></li>
	   <li><a href='catalog.do'><span>Products</span></a></li>
	   <li><a href="myaccount.do">My account</a></li>
	   <li><a href="register.do">Sign Up</a></li>
	   <li><a href="contact.do">Contact Us</a></li>
	</ul>
  </div>
