<%-- 
    Document   : StudentMain
    Created on : Dec 21, 2020, 2:43:29 AM
    Author     : JKc
--%>

<%@page import="ict.db.EquipmentDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="equipment" %>
<jsp:useBean id="user" scope="session" class="ict.bean.UserInfo" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
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
    <li><a class="active" href="StudentMain.jsp">Home</a></li>
    <li><a href="BorrowEquipment.jsp">Equipment reservation</a></li>
    <li><a href="ListPersonalBorrowingRecord.jsp">Check personal borrowing records</a></li>
    <li>
        <form method="POST" action="LoginServlet">
            <input type="submit" value="Logout" class="logout">
        </form>
    </li>
    </ul>
    </nav>
        
    <div class="mainContent">
        <center><h1 style="margin-top: 50px;">Welcome Student, <%=user.getUsername()%>!</h1></center>
    </div>
        
    </body>
</html>
