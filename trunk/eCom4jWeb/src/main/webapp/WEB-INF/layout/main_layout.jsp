
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>  
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<title><tiles:getAsString name="htmlTitle"/></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/navigation.css" media="screen" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/slides.css" />
<link rel="stylesheet" href="css/tabs.css" type="text/css">

<script type="text/javascript" src="js/scripts.js"></script>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script src="js/jquery-scrollTo.js"></script>
<script src="js/slides.min.jquery.js"></script>
</head>

<body>
	<div id="main_container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="leftPanel" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
