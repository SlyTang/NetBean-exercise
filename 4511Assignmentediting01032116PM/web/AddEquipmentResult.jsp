<%-- 
    Document   : AddEquipmentResult
    Created on : Dec 24, 2020, 10:50:04 PM
    Author     : JKc
--%>

<%@page import="ict.db.EquipmentDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/addEquipment-taglib.tld" prefix="addEquipment" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Equipment Result</title>
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
        
        <div class="mainContent" style="margin-left: 15px;">
         <%
             String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        EquipmentDB db = new EquipmentDB(dbUrl, dbUser, dbPassword);
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String typeName = db.getTypeName(type);
            String dateLimit = request.getParameter("rantDateLimit");
            String detail = request.getParameter("detail");
        %>  
        Name : <%=name%><br/>
        Type : <%=typeName%><br/>
        Date Limit : <%=dateLimit%><br/>
        Detail : <%=detail%><br/>
        <addEquipment:AddEquipmentTag name="<%=name%>" type="<%=type%>" dateLimit="<%=dateLimit%>" detail="<%=detail%>"/>
        </div>
    </body>
</html>
