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
		//alert('hi')
		$("#btnSubmit").attr("disabled",true);
		
		//create servlet and come back
		$("#btnRequestOtp").click(function(){
			var email=$("#email").val();
			if(email==null || email=="")
				return;
			$.ajax({url:"generate?email="+email, success:function(result){
				alert(result);
				$("#originalOtp").val(result);
			}});
		});
		$("#enteredOtp").keyup(function(){
			var enteredOtp=$(this).val();
			var originalOtp=$("#originalOtp").val();
			if(enteredOtp==originalOtp)
				$("#btnSubmit").attr("disabled",false);
			else
				$("#btnSubmit").attr("disabled",true);
		});
		
	});
</script>
</head>
<body>...
	<div class="jumbotron">
		<h1>
			<center>User Signup Form</center>
		</h1>
	</div>
	<div class="row">
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<form action="signup">
				User Id: <input type=text name="userId" class="form-control" required /> Password: <input
					type=password name="password" class="form-control" required /> Confirm Password: <input
					type="password" name="cpassword" class="form-control" required /> First Name: <input type=text
					name="firstName" class="form-control" required /> Last Name: <input type=text name="lastName" class="form-control" required />
				Role: <select name="role" class="form-control" required>
					<option>Choose...</option>
					<option value="user">User</option>
					<option value="admin">Admin</option>
				</select>
				Email:
				<input type=email id=email name=email class="form-control" required />
				Enter Otp:
				<input type=hidden id="originalOtp" name="originalOtp" /><br/>
				<input type=text id="enteredOtp" name="enteredOtp" />
				<input id=btnRequestOtp type=button value="Request Otp" /><br/>
				 
				<input id="btnSubmit" type=submit class="btn btn-success" />
				<input type=reset class="btn btn-info" />
					Already have account? Click
	<a href="login.jsp"> here </a>to login..
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>

</body>
</html>