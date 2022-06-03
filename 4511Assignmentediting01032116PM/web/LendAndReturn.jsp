<%-- 
    Document   : LendAndReturn
    Created on : Jan 4, 2021, 7:06:11 AM
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
    <li><a href="ListLatedEquipmentRequest.jsp">Lookup overdue items</a></li>
    <li>
        <form method="POST" action="LoginServlet">
            <input type="submit" value="Logout" class="logout">
        </form>
    </li>
    </ul>
    </nav>
        
    <div class="mainContent">
        <center>
        <form action="LendAndReturnHandler" method="POST">
            <label for="borrowRequestID" >Request ID :</label><input name="borrowRequestID"><br/>
            <label for="lend">Lend</label><input type="radio" name="type" id="lend" value="lend">
            <label for="return">Return</label><input type="radio" name="type" id="return" value="return"><br/>
            <label for="equipmentID">Equipment ID :</label><input name="equipmentID" ><br/>
            <input type="submit">
            
        </form>
        </center>
    </div>
    </body>
</html>
