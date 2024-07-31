<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function(){
		$("#chkShowPassword").change(function(){
			var show=$("#chkShowPassword").prop("checked");
			
			if(show)
			{
				//show password
				
				$("#password").attr("type","text");
			}else
			{
				//mask password
				$("#password").attr("type","password");
			}
		});
	});
</script>
</head>
<body>
	<div class="jumbotron">
		<h1>
			<center>User Login Form</center>
		</h1>
	</div>
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form action="login">
				User Name: <input type=text name="userId" class="form-control" value="${cookie.userId.value }" /><br /> 
				Password: <input type=password id="password" name="password" class="form-control" value="${cookie.password.value }" /><br />
				
					<input type=checkbox id="chkRememberMe" name="chkRememberMe" value="Remember Me" />
					<label for="chkRememberMe">Remember Me</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type=checkbox id="chkShowPassword" name="chkShowPassword" value="Show Password" />
					<label for="chkShowPassword">Show Password</label>
					<br/> 
					<input type=submit class="btn btn-success" />
			</form>
			New user? Click<a href="signup.jsp"> here </a>to signup! <br />
			<%
				String msg = request.getParameter("msg");
				if (msg != null)
					out.print(msg);
			%>
		</div>
	</div>

</body>
</html>