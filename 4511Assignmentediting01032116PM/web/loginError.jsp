<%-- 
    Document   : loginError
    Created on : Dec 20, 2020, 6:16:49 PM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
        <p>Incorrect Password</p>
        <% out.println("<a href=\"login.jsp\">Login again"); %>
    </body>
</html>
