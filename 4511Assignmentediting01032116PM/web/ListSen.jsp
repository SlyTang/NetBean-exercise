<%-- 
    Document   : ListSen
    Created on : 2020年12月29日, 上午02:21:23
    Author     : Administrator
--%>

<%@page import="ict.bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <nav>
            <label class="logo">IVPET </label>
            <ul>
        <li><a class="active" href="SeniorTechnician.jsp">Home</a></li>
            <li><a href="#">//Check the analytic & report<i class="fas fa-caret-down"></i></a>
                <ul>
                    <li><a href="#">Python</a></li>
                    <li><a href="#">JQuery</a></li>
                    <li><a href="#">Javascript</a></li>
                </ul>
            </li>

                <li><a>Account management<i class="fas fa-caret-down"></i></a>
                    <ul>
                        <li><a href="TestAddUser.jsp">Add User</a></li>
                        <li><a href="ListUser.jsp">List User</a></li>
                    </ul>
                </li>

            <li>
                <form method="POST" action="LoginServlet">
                    <input type="submit" value="Logout" class="logout">
                </form>
            </li>
            </ul>
        </nav>
        
<div class="mainContent">
    <center>
        <jsp:useBean id="user" scope="request" class="java.util.ArrayList<ict.bean.UserBean>" />
        <%
            out.println("<h1>Sen</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Username</th><th>Password</th><th>Permission</th><th colspan= 2>Action</th></tr>");
            for (int i = 0; i < user.size(); i++) {
                UserBean u = user.get(i);
                out.println("<tr>");
                out.println("<td>" + u.getId() + "</td>");
                out.println("<td>" + u.getUsername() + "</td>");
                out.println("<td>" + u.getPassword() + "</td>");
                out.println("<td>" + u.getPermission() + "</td>");
                
                out.println("<td><a href=\"handleUser?action=getEditUser&id=" + u.getId() + "\">Edit</a></td>");
                out.println("<td><a href=\"handleUser?action=delete&id=" + u.getId() + "\" onclick=\"return confirm('Are you sure to delete id " + u.getId() + "?')\">Delete</a></td>");
                
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<form><input type='button' value='Go back!' onclick='history.back()'></form>");
        %>
    </center>
</div>
    </body>
</html>
