<%-- 
    Document   : TechnicianMain
    Created on : Dec 21, 2020, 3:37:49 AM
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
        <%
            String dbUrl = "jdbc:mysql://localhost:3306/4511_assignment";
            String dbUser = "root";
            String dbPassword = "";
            EquipmentDB eqmdb = new EquipmentDB(dbUrl, dbUser, dbPassword);;
            String lateAlert = eqmdb.getLateRequest(user.getUserID());
            if(lateAlert!= ""){
                out.print("<script>alert('"+lateAlert+"');</script>");
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <nav>
    <label class="logo">IVPET </label>
    <ul>
    <li><a class="active" href="TechnicianMain.jsp">Home</a></li>
    <li><a href="#">Inventory management<i class="fas fa-caret-down"></i></a>
        <ul>
            <li><a href="AddEquipment.jsp">Add Equipment</a></li>
            <li><a href="AddEquipmentType.jsp">Add Equipment Type</a></li>
            <li><a href="ListEquipmentType.jsp">Show Equipment</a></li>
            <li><a href="ProcessRequest.jsp">Confirm/decline borrowing request</a></li>
        </ul>
    </li>
        <li><a href="LendAndReturn.jsp"> Handle check-in/out of equipment</a></li>
    <li>
        <form method="POST" action="LoginServlet">
            <input type="submit" value="Logout" class="logout">
        </form>
    </li>
    </ul>
    </nav>
        
        <div class="mainContent">
        <center><h1 style="margin-top: 50px;">Welcome Technician, <%=user.getUsername()%>!</h1></center>
        <div style="margin-left: 25%; width: 50%;">
           
        <%@include file="ListLatedEquipmentRequest.jsp" %>
            
        </div>
    </div>
    </body>
</html>