package com.servlet;

import java.io.IOException;

import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.data.BlogsData;
import com.entities.Blogs;
import com.entities.User;
import com.helper.ConnectionProvider;
import java.io.PrintWriter;

//@WebServlet("/AddBlogServlet")
@MultipartConfig
public class UpdateBlogServlet extends HttpServlet {

    public UpdateBlogServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        int bid = Integer.parseInt(request.getParameter("bid"));

        String description = request.getParameter("description");

        // Get image data
        Part imagePart = request.getPart("image");
        String imageName = null;
        String savePath = null;
        if (imagePart != null) {
            imageName = imagePart.getSubmittedFileName();

            String randomUUID = UUID.randomUUID().toString(); // generate a random UUID
            String imageUUIDName = randomUUID + "_" + imageName; // add UUID prefix to the image name

            // Writing the file to the folder
            savePath = "C:/Users/JAYVIR CHHASATIYA/Documents/NetBeansProjects/JavaProject/src/main/webapp/image/" + imageUUIDName; // Update with your actual desktop path
            imagePart.write(savePath);

        }

        User user = (User) request.getSession().getAttribute("currentUser");
        int uid = user.getId();
        Blogs blog = new Blogs(bid, title, description, savePath, uid);
        BlogsData blogData = new BlogsData(ConnectionProvider.getConnection());
        boolean update = blogData.updateBlogById(blog);

        if (update) {
            out.println("<script>");
            out.println("alert('Blog Updated Successfully')");
            out.println("window.location='index.jsp'");
            out.println("</script>");
        } else {
            out.println("<script>");
            out.println("alert('Something went wrong. Please try again')");
            out.println("window.location='updateBlog.jsp'");
            out.println("</script>");
        }
    }
}
