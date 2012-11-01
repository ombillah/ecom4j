
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title><tiles:getAsString name="htmlTitle"/></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
<script type="text/javascript" src="js/boxOver.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script src="js/jquery-scrollTo.js"></script>
</head>
<body>
	<div id="main_container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="categories" />
		<tiles:insertAttribute name="body" />
	    <!--<tiles:insertAttribute name="cart" />-->
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
