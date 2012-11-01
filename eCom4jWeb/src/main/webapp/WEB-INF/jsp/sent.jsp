<%@ page language="java" contentType="text/html; charset=windows-1252"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ecom4j Store</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="iecss.css" />
<script type="text/javascript" src="/ecom4j/js/boxOver.js"></script>
<style type="text/css">
<!--
.style4 {
	font-size: 16px;
	font-weight: bold;
	color: #FF0000;
}
-->
</style>
</head>
<body>
<div id="main_container">
  			<jsp:include page="header.jsp"  flush="false" />
      		<jsp:include page="categories.jsp"  flush="false" />

    <div class="center_content style4">
Message Successfully Sent. You will receive a response within 48 hours.    </div>
</div>
<!-- end of main content -->
         <jsp:include page="footer.jsp"  flush="false" />
<!-- end of main_container -->
</body>
</html>
