<%-- 
    Document   : login
    Created on : Dec 20, 2020, 2:24:45 AM
    Author     : JKc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/FormCSS.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <form class="box" method="POST" action="LoginServlet">
                            <h1>Login</h1>
                            <input type="hidden" name="action" value="authenticate" />
                            <p class="text-muted"> Please enter your login and password!</p> 
                            <input type="text" name="username" placeholder="Username"> 
                            <input type="password" name="password" placeholder="Password"> 
                            <input type="submit" value="Login">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    
    
</html>
