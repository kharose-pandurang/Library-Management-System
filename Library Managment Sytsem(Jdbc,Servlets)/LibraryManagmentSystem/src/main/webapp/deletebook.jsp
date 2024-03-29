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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <nav>
        <div class="nav-wrapper">
          <a href="home.jsp" class="brand-logo"><i class="material-icons right">import_contacts</i></a>
          <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="adminpage.jsp"><i class="material-icons left">arrow_back</i>Back</a></li>
          </ul>
        </div>
      </nav>
      <div class="dcard">
        <div  style="display: flex; flex-direction: column; margin-right: 100px;">
            <form action="deletebook" method="post">
                <div class="row">
                    <div class="input-field col s12">
                      <input id="email" type="text" class="validate" name="bname">
                      <label for="email">Book Name</label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s12">
                      <input id="email" type="text" class="validate" name="aname">
                      <label for="email">Author Name</label>
                    </div>
                  </div>
                  <button class="btn waves-effect waves-light button1" type="submit" name="action" style="width: 100%;">Delete
                    <i class="material-icons right">delete</i>
                  </button>
            </form>
        </div>
        <div class="t1 table1">
        	<%
        		ResultSet rs=(ResultSet)request.getAttribute("blist");
        		String msg=(String)request.getAttribute("msg");
        		int count=0;
        		if(msg=="yes"){	
        	%>
        	<script>
        	alert("Book Deleted Successfully");
        	</script>
        	<%}else if(msg=="no"){ %>
        	<script>
        	alert("No Book Found");
        	</script>
        	<%} %>
            <h5>All Books</h5>
            <table class=" responsive-table">
                <thead>
                  <tr>
                      <th>srNo</th>
                      <th>Book Name</th>
                      <th>Author</th>
                      <th>Jonor</th>
                      <th>Quantity</th>
                  </tr>
                </thead>
        
                <tbody>
                <%while(rs.next()){count++; %>
                  <tr>
                    <td><%=count %></td>
                    <td><%=rs.getString(1) %></td>
                    <td><%=rs.getString(2) %></td>
                    <td><%=rs.getString(3) %></td>
                    <td><%=rs.getInt(4)%></td>
                  </tr>
             	<%  } %>
                </tbody>
              </table>
                    
        </div>
      </div>
      
</body>
</html>