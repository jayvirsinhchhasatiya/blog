package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.data.BlogsData;
import com.entities.Blogs;
import com.entities.User;
import com.helper.ConnectionProvider;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//@WebServlet("/AddBlogServlet")
@MultipartConfig
public class AddBlogServlet extends HttpServlet {

//    private static final long serialVersionUID = 1L;

    public AddBlogServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

//        String path =  request.getServletContext().getRealPath("/");
//        out.println(path);
        String title = request.getParameter("title");
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
// Writing the file to the folder
//            savePath = request.getServletContext().getRealPath("/") + "image/" + imageUUIDName;
//            imagePart.write(savePath);

//            String randomUUID = UUID.randomUUID().toString(); // generate a random UUID
//            String imageUUIDName = randomUUID + "_" + imageName; // add UUID prefix to the image name
//
//            // Writing the file to the folder
//            savePath = request.getServletContext().getRealPath("/") + "image/" + imageUUIDName;
//            File fileSaveDir = new File(savePath);
//            imagePart.write(fileSaveDir.getAbsolutePath());
        }

        User user = (User) request.getSession().getAttribute("currentUser");
        int uid = user.getId();
        Blogs blog = new Blogs(title, description, savePath, uid);
        BlogsData blogData = new BlogsData(ConnectionProvider.getConnection());
        boolean saved = blogData.saveBlog(blog);

        if (saved) {
            out.println("<script>");
            out.println("alert('Blog Added Successfully')");
            out.println("window.location='index.jsp'");
            out.println("</script>");
        } else {
            out.println("<script>");
            out.println("alert('Something went wrong. Please try again')");
            out.println("window.location='writeBlog.jsp'");
            out.println("</script>");
        }
    }
}
