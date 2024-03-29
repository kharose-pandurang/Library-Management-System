<%@page import="java.sql.ResultSet"%>
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
	<nav>
		<div class="nav-wrapper">
			<a href="home.jsp" class="brand-logo"><i
				class="material-icons right">import_contacts</i></a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="adminpage.jsp"><i class="material-icons left">arrow_back</i>Back</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="t2">
			<h6>Aprove Pending</h6>
			<table class="responsive-table">
				<thead>
					<tr>
						<th>StudentID</th>
						<th>Book Name</th>
						<th>Approvals</th>
					</tr>
				</thead>

				<tbody>
					<%
					ResultSet rs = (ResultSet) request.getAttribute("rlist");
					String msg = (String) request.getAttribute("msg");
					ResultSet rs1 = (ResultSet) request.getAttribute("relist");
					String msg1 = (String) request.getAttribute("msg1");
					int count = 0;
					if (msg =="yes"){
					%>
					<script>
						alert("Verified")
					</script>
					<%
					}
					while (rs.next()) {
					%>
					<tr>
						<form action="grant" method="post">
							<td><input type="text" value=<%=rs.getString(2) %> name="sid"></td>
							<td><input type="text" value=<%=rs.getString(3) %> name="bname"></td>
							<td><button class="btn waves-effect waves-light button2"
									type="submit" name="action">
									verify <i class="material-icons right"
										style="margin-left: 1px;">check</i>
								</button></td>
						</form>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="t2">
			<h6>Return Pending</h6>
			<table class="responsive-table">
				<thead>
					<tr>
						<th>StudentID</th>
						<th>Book Name</th>
						<th>Approvals</th>
					</tr>
				</thead>

				<tbody>
				<%
					if (msg1=="yes"){
					%>
					<script>
						alert("Verified")
					</script>
					<%
					}
					while (rs1.next()) {
					%>
					<tr>
						<form action="grantreturn" method="post">
							<td><input type="text" value=<%=rs1.getString(2) %> name="sid"></td>
							<td><input type="text" value=<%=rs1.getString(3) %> name="bname"></td>
							<td><button class="btn waves-effect waves-light button2"
									type="submit" name="action">
									verify <i class="material-icons right"
										style="margin-left: 1px;">check</i>
								</button></td>
						</form>
					</tr>
					<%} %>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>