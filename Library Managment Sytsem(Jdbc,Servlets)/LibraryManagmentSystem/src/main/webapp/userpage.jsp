<%@page import="DAO.Student"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="style/style.css">
</head>
<body>
	<%
	String msg = "error";
	msg = (String) request.getAttribute("msg");
	Student student=(Student)request.getAttribute("student");
	if (msg == "yes") {
	%>
	<script>
		alert("Login Successfull!");
	</script>
	<%
	}
	%>
	<nav>
		<div class="nav-wrapper">
			<a href="home.jsp" class="brand-logo"><i
				class="material-icons right">import_contacts</i></a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="home.jsp"><i class="material-icons left">arrow_back</i>Back</a></li>
			</ul>
		</div>
	</nav>
	<h4 class="titel">Welcome</h4>
	<div class="card1">
		<form action="lissuebooks" method="post" style="width: 100%;">
			<button class="waves-effect waves-light btn button1" type="submit">
				<i class="material-icons left">delete</i>Issue An Book
			</button>
		</form>
		<form action="lstudentissue" method="post" style="width: 100%;">
		    <input type="hidden" value=<%=student.getSid() %> name="sid">
			<button class="waves-effect waves-light btn button1" type="submit">
				<i class="material-icons left">delete</i>View And Return
			</button>
		</form>
	</div>

</body>
</html>