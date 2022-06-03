<%-- 
    Document   : TestAddUser
    Created on : Dec 23, 2020, 2:29:55 AM
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
        <li><a class="active" href="SeniorTechnician.jsp">Home</a></li>
            <li><a href="#">//Check the analytic & report<i class="fas fa-caret-down"></i></a>
                <ul>
                    <li><a href="#">Python</a></li>
                    <li><a href="#">JQuery</a></li>
                    <li><a href="#">Javascript</a></li>
                </ul>
            </li>

                <li><a>Account management<i class="fas fa-caret-down"></i></a>
                    <ul>
                        <li><a href="TestAddUser.jsp">Add User</a></li>
                        <li><a href="ListUser.jsp">List User</a></li>
                    </ul>
                </li>

            <li>
                <form method="POST" action="LoginServlet">
                    <input type="submit" value="Logout" class="logout">
                </form>
            </li>
            </ul>
        </nav>
        
        <div class="mainContent">
            <center>
        <form action="CreateUser" method="POST">
            <input type="hidden" value="createUser" name="action">
            <label for="username">Username: </label><input name="username"><br/>
            <label for="password">Password: </label><input name="password" type="password"><br/>
            <label for="permission">Permission: </label>
            <label for="stu">stu</label><input type="radio" id="stu" name="permission" value="stu">
            <label for="tec">tec</label><input type="radio" name="permission" id="tec" value="tec">
            <label for="sen">sen</label><input type="radio" name="permission" id="sen" value="sen"><br/>
            <input type="submit">
        </form>
            </center>
        </div>
    </body>
</html>
