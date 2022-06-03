<%-- 
    Document   : editUser
    Created on : 2020年12月29日, 上午12:46:10
    Author     : Administrator
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
        <script>
            function getText() {
                String type = document.getElementById(action).value;
                String id = document.getElementById(id).value;
                String username = document.getElementById(username).value;
                String password = document.getElementById(password).value;
                String permission = document.getElementById(permission).value;
                String text = '';
                    text += 'Are you sure to update the following User informaion?';
                    text += '\nID: ' + id;
                    text += '\nUsername: ' + username;
                    text += '\nPassword: ' + password;
                    text += '\nPermission: ' + permission;
                console.log(text);
                return text;
            }
        </script>
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
        
        <jsp:useBean id="u" scope="request" class="ict.bean.UserBean" />
        
<div class="mainContent">
    <center>
        <table>
    <form method="get" action="handleEdit" onsubmit="return confirm(getText());" >
        <input type="hidden" name="action" id="ac   tion" value="edit" />
        
        <tr><td>ID </td><td><input name="id" id="id" type="text" readonly value="<%=u.getId()%>" /></td></tr>
        <tr><td>Username </td><td><input name="username" id="username" type="text" value="<%=u.getUsername()%>" /></td></tr>
        <tr><td>Password </td><td><input name="password" id="password" type="text" value="<%=u.getPassword()%>" /></td></tr>
        <tr><td>Permission </td><td><input name="permission" id="permission" type="text" value="<%=u.getPermission()%>" /></td></tr>
        <tr><td colspan="2"><center><input type="submit" value="submit" onclick="return confirm(getText());" />
        <form>
            <input type="button" value="Go back!" onclick="history.back(-1)">
        </form></center>
        </td></tr>
        </table>
    </form>
    </center>
</div>
    </body>
</html>
