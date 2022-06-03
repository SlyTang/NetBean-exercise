<%-- 
    Document   : BorrowEquipmentSelectDate
    Created on : Jan 3, 2021, 3:17:55 AM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="equipment" %>
<jsp:useBean id="user" scope="session" class="ict.bean.UserInfo" />
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" href="jquery/jquery-ui/jquery-ui.css" rel="stylesheet" />
        
        <script src="jquery/jquery-1.11.1.js"></script>
        <script src="jquery/jquery-ui/jquery-ui.js"></script>
        <script>
            function updateEndDate(){
                   var limitDate = parseInt($("#dateLimit").val());
                   var endDate = $('#from').datepicker('getDate');
                   endDate.setDate(endDate.getDate()+limitDate)
                   $( "#to" ).datepicker("setDate", endDate);
            }
            
            $(document).ready(function(){
                $("#dateLimit").change(function(){
                   updateEndDate();
                });
                
                $("#rentEquipments").submit(function(){
                    var rentDate = $("#dateLimit").val();
                    var to = $("#to").val();
                    var error = "";
                    if(rentDate == ""){
                        error += "Please input value limit";
                    }
                    if(to == ""){
                        error += "\nPlease input the rent starting date"
                    }
                    if(error!= ""){
                        alert(error);
                        return false;
                    }
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
        <% String maxRentDate = request.getParameter("rentDate");
           String[] selectedEquipments = request.getParameterValues("equipment");%>
           
        <form name="rentEquipments" id="rentEquipments" action="AddNewBorrowingRecord" method="POST">
           <equipment:ListSelectedEquipment equipmentsID="<%=selectedEquipments%>" />
        
            Rent Day: <input type="number" id="dateLimit" name="dateLimit" min="1" max="<%=maxRentDate%>" value="<%=maxRentDate%>"><br/>        
            <label for="from">From</label> <input type="text" id="from" name="from"/>
            <label for="to">to</label> <input type="text" id="to" name="to" disabled="disabled"/>
            <input type="hidden" value="<%=user.getUserID()%>" name="userID" id="userID">
            <script>
            var dateToday = new Date();    
            var dates = $("#from, #to").datepicker({
                dateFormat: 'yy-mm-dd' ,
                defaultDate: "+1w",
                changeMonth: true,
                numberOfMonths: 1,
                minDate: dateToday,
                onSelect: function(selectedDate) {
                    updateEndDate();
                }
            });
            </script><br/>
            <input type="submit">
        </form>
        </center>
    </div>
    </body>
</html>
