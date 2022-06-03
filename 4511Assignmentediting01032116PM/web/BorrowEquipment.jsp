<%-- 
    Document   : BorrowEquipment
    Created on : Jan 2, 2021, 12:32:39 PM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Equipment-taglib.tld" prefix="ListEquipmentTag" %>
<jsp:useBean id="user" scope="session" class="ict.bean.UserInfo" />
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="CSS/MainContentCSS.css">
        <link rel="stylesheet" href="CSS/MenuBarCSS.css">
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<!--        <link type="text/css" href="jquery/jquery-ui/jquery-ui.css" rel="stylesheet" />
        <script src="jquery/jquery-ui/jquery-ui.js"></script>-->
        <script src="jquery/jquery-1.11.1.js"></script>
        
        <script>
            
            var rentLimitDate = null;
            function checkIsMin(newRantDateLimit) {
                
                var newInt = parseInt(newRantDateLimit);
                var oldInt = parseInt(rentLimitDate);
                if(rentLimitDate == null||oldInt > newInt){
                    rentLimitDate = newRantDateLimit;
                    $("#maxRentDate").text(rentLimitDate);
                }
            }
            
            $(document).ready(function(){
                //clicked check box
                $('input[type="checkbox"]').click(function(){
                    var numberOfChecked = $('input:checkbox:checked').length;
                    //check is any selected checkbox
                    if(numberOfChecked == 0){
                        $("#maxRentDate").text("");
                        rentLimitDate = null;
                        return ;
                    }
                    
                    var selectedID = $(this).val();
                    //check is cancel select
                    if(!$(this).is(":checked")){
                        var cancelingEquipmentDateLimit = $("#"+selectedID).text();
                        if(cancelingEquipmentDateLimit === rentLimitDate){
                            rentLimitDate = null;
                            $(':checkbox:checked').each(function () {
                                
                                var currentCheckbox = $(this).val();
                                var currentRentDay =  $("#"+currentCheckbox).text();
                                checkIsMin(currentRentDay);
                            });
                        }
                     
                    }//not cancel select
                    else{
                        var rentLimit = $("#"+$(this).val()).text();
                        checkIsMin(rentLimit);
                        
                    }
                });
                
                //click filter
                $("select[name='type']").change(function(){
                    var targetTypeName = $("select[name='type'] option:selected").text();
                    if(targetTypeName == "Select Type" ){
                        $("*").show();
                    }else{
                        $("*").show();
                        $("tr").each(function () {
                            var currentID = $(this).attr('id');
                            if(currentID != targetTypeName && currentID != "top"){
                                $(this).hide();
                            }
                        });
                    }
                   
                });
                $('#equipmentSelecting').submit(function() {
                    var numberOfChecked = $('input:checkbox:checked').length;
                    //check is any selected checkbox
                    if(numberOfChecked == 0){
                        alert("Please select the equipment!");
                        return false;
                    }else{
                        $("#rentDate").val($("#maxRentDate").text());
                        return true;
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
        <ListEquipmentTag:ListEquipmentSelectionTag/>
        <form action="BorrowEquipmentSelectDate.jsp" method="POST" id="equipmentSelecting">
            <table>
                <tr id="top">
                    <td></td>
                    <td>Equipment name</td>
                    <td>Type</td>
                    <td>Rant Limit Date</td>
                    <td>Detail</td>
                </tr>
                
                <ListEquipmentTag:ListAllEquipment />
            </table>
            
            
            
            <input type="hidden" name="rentDate" id="rentDate">
            Max rent date: <label id="maxRentDate"></label>
            <br/><input type="submit">
            </div>
        </form>
    </body>
</html>
