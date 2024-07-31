<%@page import="com.mphasis.hrms.model.User"%>
<%@page import="com.mphasis.hrms.model.Associate"%>
<%@page import="java.util.List"%>
<%@page import="com.mphasis.hrms.model.AssociateDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
	if(!user.getRole().equals("admin"))
	{
		out.println("\n<br/>Sorry. Only admin can access this page. click<a href=login.jsp> here </a>to go back to login page");
		return;
	}
%>
<jsp:include page="header.jsp" /> 
  
<jsp:useBean id="adao" class="com.mphasis.hrms.model.AssociateDaoImpl" />
<%
	List<Associate> associateList= adao.read();
	session.setAttribute("associateList", associateList);
%>
<div class="jumbotron">
	<h1><center>Associate Registration form</center></h1>
</div>s
<div class="row">
	<div class="col-md-4">
	</div>
	<div class="col-md-4">
		<form method="POST" action="associate" enctype="multipart/form-data">
			Associate Id: 
			<input type=number name="associateId" class="form-control" value="${param.associateId }" />
			First Name:
			<input type=text name="firstName" class="form-control" value="${param.firstName }" />
			Last Name:
			<input type=text name="lastName" class="form-control" value="${param.lastName }" />
			Date Of Joining:
			<input type=date name="dateOfJoining" class="form-control" value="${param.dateOfJoining }" />
			Gender:
			<input type="radio"  name="gender" value="Female" id="rdoFemale" <c:if test="${param.gender eq 'Female' }">checked</c:if> >
			<label for="rdoFemale" >Female</label>
			<input type="radio" name="gender" value="Male" id="rdoMale" <c:if test="${param.gender eq 'Male' }">checked</c:if>>
			<label for="rdoMale" >Male</label>
			<input type="radio" name="gender" value="Other" id="rdoOther" <c:if test="${param.gender eq 'Other' }">checked</c:if>>
			<label for="rdoOther" >Other</label>
			Picture:
			<input type=file name="picture" />
			<br/>
			<input type=submit class="btn btn-success" name="btn" value="Add" />&nbsp;
			<input type=submit class="btn btn-info" name="btn" value="Modify" />&nbsp;
			<input type=submit class="btn btn-warning" name="btn" value="Delete" />&nbsp;
			
		</form>
	</div>
		<div class="col-md-4">
	</div>
</div>
<div class="row">
	<div class="col-md-3">
	</div>
	<div class="col-md-6">...
		<table class="table table-striped table-bordered table-hover">
		<thead>
			<th>Associate Id</th><th>First Name</th><th>Last Name</th>
			<th>Date of Joining</th><th>Gender</th><th>Picture</th>
			<th></th>
		</thead>
		<c:forEach var="a" items="${associateList }">
		<form action="associate2.jsp">
			<tr>
				<td>
					<input type=hidden name="associateId" value="${a.associateId }" />
					${a.associateId }
				</td>
				<td>
					<input type=hidden name="firstName" value="${a.firstName }" />
					${a.firstName }
				</td>
				<td>
					<input type=hidden name="lastName" value="${a.lastName }" />
					${a.lastName }
				</td>
				<td>
					<input type=hidden name="dateOfJoining" value="${a.dateOfJoining2 }" />
					${a.dateOfJoining2 }
				</td>
				<td>
					<input type=hidden name="gender" value="${a.gender }" />
					${a.gender }
				</td>
				<td>
					<img width=80 height=80 src="data:image/jpg;base64,${a.picture1}" />
				</td>
				<td>
					<input type=submit class="btn btn-info" />
				</td>				
			</tr>
		</form>
		</c:forEach>
		</table>
	</div>
	<div class="col-md-3">
	</div>
</div>
</body>
</html>