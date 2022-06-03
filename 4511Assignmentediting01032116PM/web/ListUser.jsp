<%-- 
    Document   : ListUser
    Created on : Dec 23, 2020, 2:38:22 AM
    Author     : JKc
--%>

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
            <table border="1">
                <tr><td><h1><a href="handleUser?action=list&role=stu" >List Student</a></h1></td></tr>
                <tr><td><h1><a href="handleUser?action=list&role=tec" >List Tec </a></h1></td></tr>
                <tr><td><h1><a href="handleUser?action=list&role=sen" >List Sen </a></h1></td></tr>
            </table>
            </center>
        </div>
    </body>
</html>
