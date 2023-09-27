<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="newStyle.css">

        <style>
            .container {
                margin-top: 30px;
                margin-bottom: 30px;
            }
        </style>
    </head>

    <body>
        <div class="container-login" style="margin: 2% auto;">
            <div class="form">
                <header>Signup</header>
                    <% String error = request.getParameter("error"); %>
                    <% if (error != null) {%>
                <p style="color: red;"><%= error%></p>
                <% }%>
                <form action="RegisterServlet" method="POST">
                    <input type="text" name="name" placeholder="Enter your name">
                    <input type="text" name="email" placeholder="Enter your email">
                    <input type="password" name="password" placeholder="Create a password">
                    <input type="password" name="cpassword" placeholder="Confirm your password">
                    <button type="submit">Signup</button>
                </form>
                <div class="signup">
                    <span class="signup">Already have an account?
                        <a href="login.jsp">Login</a>
                    </span>
                </div>
            </div>
        </div>
    </body>

</html>