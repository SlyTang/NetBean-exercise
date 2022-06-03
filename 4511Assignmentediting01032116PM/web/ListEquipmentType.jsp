<%-- 
    Document   : listEquipment
    Created on : Dec 26, 2020, 11:58:57 AM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="ListEquipmentTag" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Equipment Type</title>
        <script src="jquery/jquery-1.6.js"></script>
        <script>
            $( document ).ready(function() {
                $('input[type="submit"]').click(function(){
                    $("#value").val($(this).attr('name'));
                });
            });
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
            <form method="POST" action="ListEquipment" id="selectType">
                <input type="hidden" name="value" id="value">
                <ListEquipmentTag:listEquipmentType />
        </div>
        </form>
    </body>
</html>
