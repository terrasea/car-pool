<%@page errorPage="errorPage.jsp" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="org.verisign.joid.consumer.OpenIdFilter, car.pool.persistance.*, car.pool.user.*" %>
<%
HttpSession s = request.getSession(true);

//a container for the users information
User user = null;
if(s.getAttribute("signedin") != null ) {
	user = (User)s.getAttribute("user");
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>About Us</title>
	<style type="text/css" media="screen">@import "TwoColumnLayout.css";</style>
	<%@include file="include/javascriptincludes.html" %>
</head>
<body>
	<%@ include file="heading.html" %>

	<DIV class="Content" id="Content">
		<p>A paragraph or two about our website</p>
		<p><a href="welcome.jsp">Home</a></p>
	</DIV>

<%
if (user != null) { 		//depending if the user is logged in or not different side menus should be displayed
%> 
	<jsp:include page="leftMenu.html" flush="false" />
<%
} else { 
%>
	<jsp:include page="leftMenuLogin.html" flush="false" />
<%
} 
%>

</body>
</html>