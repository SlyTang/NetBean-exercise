<%-- 
    Document   : SeniorTechnician
    Created on : Dec 23, 2020, 9:08:26 PM
    Author     : JKc
--%>

<%@page import="ict.db.EquipmentDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" scope="session" class="ict.bean.UserInfo" />
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="equipment" %>
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
        <li><a href="#">(dont done!!!)Check the analytic & report<i class="fas fa-caret-down"></i></a>
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
        <center><h1>Welcome Senior Technician, <%=user.getUsername()%>!</h1></center>
    </div>
    </body>
</html>
