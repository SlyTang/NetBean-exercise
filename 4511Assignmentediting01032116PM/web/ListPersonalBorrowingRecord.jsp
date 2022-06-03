<%-- 
    Document   : ListPersonalBorrowingRecord
    Created on : Jan 3, 2021, 11:03:42 PM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="ListEquipmentTag" %>
<jsp:useBean id="user" scope="session" class="ict.bean.UserInfo" />
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery/jquery-1.6.js"></script>
        <script>
            $( document ).ready(function() {
                $("input[type='button']").click(function(){
                   var id = $(this).val()
                   $("#selectedRequest").val(id);
                   $('#getDetail').submit();
                });
            });
        </script>
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
        <center>
        <form action="watchRequestDetail" method="POST" id="getDetail">
            <input type="hidden" name="selectedRequest" name="selectedRequest" id="selectedRequest">
            <ListEquipmentTag:ListPersonalBorrowingRecordTag userID="<%=user.getUserID()%>"></ListEquipmentTag:ListPersonalBorrowingRecordTag>
        </form>
    </center>
    </div>
    </body>
</html>
