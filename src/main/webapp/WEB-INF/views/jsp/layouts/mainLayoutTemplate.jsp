<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"></tiles:getAsString></title>
<tiles:insertAttribute name="resource" />
</head>
<body class="main-page">
<div class="container">
	<div id="container-top">
		<div id="left-sidebar" >
			<tiles:insertAttribute name="left-sidebar" />
		</div>
		<div id="right-sidebar">
			<tiles:insertAttribute name="right-sidebar" />
		</div>
		<div id="content" >
			<tiles:insertAttribute name="content" />
		</div>
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</div>
</body>
</html>	