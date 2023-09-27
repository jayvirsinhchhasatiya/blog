<%@page import="java.util.List"%>
<%@page import="com.helper.ConnectionProvider"%>
<%@page import="com.data.BlogsData"%>
<%@page import="com.entities.Blogs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--for navbar-->
        <link rel="stylesheet" href="navbar.css" />
        <link rel="stylesheet" href="home.css" />
        <title>Blogging</title>
    </head>
    <body>
        
        <!--navbar-->
        <%@include file="navbar.jsp" %>
        
        

        <!--main section-->
        <section class="showcase" id="explore-games">
            <div class="container">
                <% 
                    Connection conn = ConnectionProvider.getConnection();
                    BlogsData blogsData = new BlogsData(conn);
                    List<Blogs> blogsList = blogsData.getAllBlogs();
                    
                    for (Blogs blog : blogsList) {
                %>
                <div class="row-blog row1" data-aos="fade-left">
                    <div class="img-box">
                        <img src="<%= blog.getPic() %>" alt="">
                    </div>
                    <div class="text-box">
                        <h2 class="large-heading text-black"><%= blog.getTitle().substring(0, Math.min(blog.getTitle().length(), 15)) %>..</h2>
                        <p class="text-gray"><%= blog.getDescription().substring(0, Math.min(blog.getDescription().length(), 200)) %>...</p>

                        <a href="readBlog.jsp?bid=<%= blog.getBid() %>" class="button button-sec">Read More</a>
                    </div>
                </div>
                <% } %>
            </div>
        </section>
        
        

        <!--footer-->
        <%@include file="footer.jsp" %>
        
        

        <!-- js for navbar-->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="navbar.js"></script>
    </body>
</html>
