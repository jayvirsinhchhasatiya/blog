<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>login form</title>
        <link rel="stylesheet" href="newStyle.css">
    </head>

    <body>
        <div class="container-login">
            <!-- <input type="checkbox" id="check"> -->
            <div class="login form">
                <header>Login</header>
                    <% String message = request.getParameter("message"); %>
                    <% if (message != null) {%>
                <p style="color: green"><%= message%></p>
                <% } %>
                <% String error = request.getParameter("error"); %>
                <% if (error != null) {%>
                <p style="color: red;"><%= error%></p>
                <% }%>
                <form action="LoginServlet" method="POST">
                    <input type="text" name="email" placeholder="Enter your email">
                    <input type="password" name="password" placeholder="Enter your password">
                    <button type="submit">Login</button>
                </form>
                <div class="signup">
                    <span class="signup">Don't have an account?
                        <a href="registration.jsp">Signup</a>
                    </span>
                </div>
            </div>
        </div>
    </body>

</html>