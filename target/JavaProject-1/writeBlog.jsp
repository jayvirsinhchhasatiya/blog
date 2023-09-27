<%@page import="com.entities.User" %>
<%@page errorPage="error.jsp"%>


<%

    User user = (User) session.getAttribute("currentUser");

    if (user == null) {

        response.sendRedirect("login.jsp");
    }


%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Read Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


        <link rel="stylesheet" href="navbar.css" />
        <link rel="stylesheet" href="writeBlog.css">
        <link rel="stylesheet" href="footer.css">



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>

    <body>

        <!--navbar-->
        <%@include file="navbar.jsp" %>


        <!--form for blog-->
        <div class="container mt-4">
            <form class="my-form" action="AddBlogServlet" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="10" required></textarea>
                </div>
                <div class="form-group">
                    <label for="image">Image</label>
                    <div class="custom-file">
                        <input type="file" class="custom-file-input" id="image" name="image" required>
                        <label class="custom-file-label" for="image">Choose file</label>
                    </div>
                    <div class="preview mt-3">
                        <img id="preview-image" class="img-fluid" src="#" alt="Preview Image">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block mt-3">Submit</button>
            </form>
        </div>


        <!--footer-->
        <%@include file="footer.jsp" %>


        <!--js for image-->
        <script>
            $(document).ready(function () {
                // Preview uploaded image
                $("#image").change(function () {
                    readURL(this);
                });
            });

            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $("#preview-image").attr("src", e.target.result);
                        $(".preview").show();
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }

        </script>


        <!-- js for navbar-->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="navbar.js"></script>



        <%--<%= // user.getName()%>--%>
        <%--<%= // user.getEmail()%>--%>
        <%--<%= // user.getPassword()%>--%>


    </body>

</html>
