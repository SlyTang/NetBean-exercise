<%-- 
    Document   : ListOthersBorrowingRecord
    Created on : Jan 4, 2021, 3:42:02 AM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="ListEquipmentTag" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Others Borrowing Record</title>
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
        
    <div class="mainContent">
        <center>
        <%
            String userID  = request.getParameter("userID");
        %>
        <form action="watchRequestDetail" method="POST" id="getDetail">
            <input type="hidden" name="selectedRequest" name="selectedRequest" id="selectedRequest">
            <ListEquipmentTag:ListPersonalBorrowingRecordTag userID="<%=userID%>"></ListEquipmentTag:ListPersonalBorrowingRecordTag>
            
        </form>
    </center>
    </div>
    </body>
</html>