
<%@page import="com.entities.*" %>
<%@page errorPage="error.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.helper.ConnectionProvider"%>
<%@page import="com.data.BlogsData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>

<%

    User user = (User) session.getAttribute("currentUser");

    if (user == null) {
        response.sendRedirect("login.jsp");
    }


%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <link rel="stylesheet" href="profile.css">
        <link rel="stylesheet" href="navbar.css" />
        <link rel="stylesheet" href="footer.css" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <style>
            label{
                font-size: 20px;
            }
            .form-control {
                font-size: 15px;
            }

            .form-group a {
                font-size: 20px;
                margin: 23px auto;
            }
        </style>
    </head>



    <!--navbar-->
    <%@include file="navbar.jsp" %>


    <!--userprofile-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 mx-auto">

                <h1>User Profile</h1>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" value="<%= user.getName()%>" readonly>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" value=" <%= user.getEmail()%>" readonly>
                </div>
                <div class="form-group">
                    <a class="btn btn-danger btn-sm btn-block" href="LogoutServlet">Logout</a>
                </div>

            </div>
        </div>
    </div>




    <!--table of all blogs-->

    <div class="container-fluid">
        <h1>Example Table</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Sr. No.</th>
                    <th>Title</th>
                    <th>Read</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Connection conn = ConnectionProvider.getConnection();
                    BlogsData blogsData = new BlogsData(conn);
                    List<Blogs> blogsList = blogsData.getAllBlogsByuid(user.getId());

                    int counter = 1;
                    for (Blogs blog : blogsList) {
                %>
                <tr>
                    <td><%= counter%></td>
                    <td><%= blog.getTitle()%></td>
                    <td><a href="readBlog.jsp?bid=<%= blog.getBid()%>" class="btn btn-primary">Read</a></td>
                    <td><a href="updateBlog.jsp?bid=<%= blog.getBid()%>" class="btn btn-success">Update</a></td>
                    <td>
                        <a href="#" onclick="deleteBlog(<%= blog.getBid()%>)" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
                <% counter++;
                    }%>
                <!-- Add more rows as needed -->
            </tbody>
        </table>
    </div>





    <%--<%= user.getPassword()%>--%>

    <!--footer-->
    <%@include file="footer.jsp" %>



    <!-- js for navbar-->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script src="navbar.js"></script>

    <!--for delete-->

    <script>
                            function deleteBlog(blogId) {
                                if (confirm('Are you sure you want to delete this blog?')) {
                                    var xhr = new XMLHttpRequest();
                                    xhr.open('POST', 'DeleteBlogServlet?bid=' + blogId);
                                    xhr.onload = function () {
                                        if (xhr.status === 200) {
                                            alert('Blog deleted successfully');
                                            location.reload();
                                        } else {
                                            alert('An error occurred while deleting the blog');
                                        }
                                    };
                                    xhr.send();
                                }
                            }
    </script>

</html>
