<%@page import="com.mphasis.hrms.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
    function preventBack() {
        window.history.forward();
    }

    setTimeout("preventBack()", 0);
    window.onunload = function() {
        null
    };
</script>
</head>
<body>
<%

	if(session.getAttribute("user")==null)
	{
		out.println("YOu have not logged in. Click<a href=login.jsp> here </a>to go to login page");
		return;
	}
	User user=(User)session.getAttribute("user");
	out.println("Welcome "+user.getFirstName()+"... You are an "+user.getRole());
%>
<jsp:include page="header.jsp" /> 
Welcome to home page
</body>
</html>