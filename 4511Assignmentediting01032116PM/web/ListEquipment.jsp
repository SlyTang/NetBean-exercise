<%-- 
    Document   : ListEquipment
    Created on : Dec 27, 2020, 3:43:56 AM
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
        <title>List Equipment</title>
        <style>
            tr{
                border:1px solid;
            }
        </style>
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
        </ul>
    </li>
        <li><a href="#"> (dont done!!!)Handle check-in/out of equipment<i class="fas fa-caret-down"></i></a>
            <ul>
            <li><a href="#">Front End</a></li>
            <li><a href="#">Back End</a></li>
                <li><a href="#">Others<i class="fas fa-caret-right"></i></a>
                    <ul>
                        <li><a href="#">Links</a></li>
                        <li><a href="#">Works</a></li>
                        <li><a href="#">Status</a></li>
                    </ul>
                </li>
            </ul>
        </li>
    <li><a href="#">//Lookup overdue items</a></li>
    <li>
        <form method="POST" action="LoginServlet">
            <input type="submit" value="Logout" class="logout">
        </form>
    </li>
    </ul>
    </nav>
        <div class="mainContent">
            <% String type = request.getParameter("type");%>
        </div>
    </body>
</html>
