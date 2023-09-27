<%@page import="com.entities.*"%>
<%@page import="com.data.BlogsData"%>
<%@page import="com.data.UserData"%>
<%@page import="com.helper.ConnectionProvider"%>
<%@page import="java.sql.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="navbar.css" />
        <link rel="stylesheet" href="footer.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <title>Blog Full Screen View</title>

        <style>
            .container {
                border-radius: 20px;
                margin-top: 20px;
                background-color: #f7f7f7;
            }
            .blog-title {
                font-size: 48px;
                font-weight: bold;
                text-align: center;
            }
            .blog-image {
                max-height: 500px;
                margin: auto;
                display: block;
            }
            .blog-writer {
                font-size: 24px;
                font-weight: bold;
                text-align: center;
                margin-bottom: 30px;
            }
            .blog-description pre {
                font-family: inherit;
                font-size: 20px;
                text-align: justify;
                padding: 20px 10px;
                margin-bottom: 40px;
            }
        </style>
    </head>
    <body>

        <!--navbar-->
        <%@include file="navbar.jsp" %>

        <div class="container">


            <%

                String bId = request.getParameter("bid");
                // get the blog ID from the request parameter
                int blogId = Integer.parseInt(bId);

                // get the blog data from the database
                Connection conn = ConnectionProvider.getConnection();
                BlogsData blogsData = new BlogsData(conn);
                Blogs blog = blogsData.getBlogById(blogId);

                // get the user data from the database
                UserData usersData = new UserData(conn);
                String authorName = usersData.getNameById(blog.getUid());

                //            User u = usersData.getUserById(blog.getUid());
                // close database connection
                //            conn.close();
%>


            <!-- Display the blog title in big, bold letters -->
            <div class="blog-title">
                <%= blog.getTitle()%>
            </div>

            <!-- Display the blog image -->
            <img class="blog-image" src="<%= blog.getPic()%>">

            <!-- Display the writer's name -->
            <div class="blog-writer">
                Written by <%= authorName%>
            </div>

            <!-- Display the blog description -->
            <div class="blog-description">
                <pre><%= blog.getDescription()%></pre>
            </div>
        </div>


<!--        <img src="<%=blog.getPic()%>" alt="Blog Image" style="max-width: 100%; height: auto;">
<p>Written by: <%= authorName%></p>
<p><%=blog.getDescription()%></p>-->

        <!--footer-->
        <%@include file="footer.jsp" %>



        <!-- js for navbar-->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="navbar.js"></script>

    </body>
</html>
