<%-- 
    Document   : AddEquipment
    Created on : Dec 23, 2020, 9:15:53 PM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="equipment" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Equipment</title>
        
        <script>
           
        </script>
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
            <form action="AddEquipmentResult.jsp" method="POST">
                <table style="display:inline-block;">
                    <tr ><td><label for="name">Name: </label></td></tr>
                    <tr ><td><label for="type">Type: </label></td></tr>
                    <tr><td><label for="rantDateLimit">Rant day limit: </label></td></tr>
                    <tr><td><label for="detail">Detail: </label></td></tr>

                </table>
                <table style="display:inline-block">
                    <tr><td><input name="name"></td></tr>
                    <tr><td><equipment:ListEquipmentSelectionTag/></td</tr>
                    <tr><td><input name="rantDateLimit" type="number" min="1"></td></tr>
                    <tr><td><textarea name="detail" rows="1"></textarea></td></tr>
                </table>
                <br/>
                <input type="submit">
            </form>
        </div>
    </body>
</html>
