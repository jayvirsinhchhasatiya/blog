////package com.servlet;
////
////import com.data.BlogsData;
////import com.entities.Blogs;
////import com.helper.ConnectionProvider;
////import java.io.File;
////import java.io.IOException;
////import java.io.PrintWriter;
////import javax.servlet.ServletException;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////
////public class DeleteBlogServlet extends HttpServlet {
////
////    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        response.setContentType("text/html;charset=UTF-8");
////        PrintWriter out = response.getWriter();
////
////        int bid = Integer.parseInt(request.getParameter("bid"));
////       
////        BlogsData blogData = new BlogsData(ConnectionProvider.getConnection());
////
////        Blogs blog = new Blogs(bid);
////
////        String path = blog.getPic();
////        try {
////            boolean delete = blogData.deleteBlogById(bid);
////
////            if (delete) {
////
////                File f = new File(path);
////                if (f.exists()) {
////                    f.delete();
////                }
//////                out.println("<script>");
//////                out.println("alert('Blog Deleted Successfully')");
//////                out.println("window.location='index.jsp'");
//////                out.println("</script>");
////            } else {
//////                out.println("<script>");
//////                out.println("alert('Something went wrong. Please try again')");
//////                out.println("window.location='updateBlog.jsp'");
//////                out.println("</script>");
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////    }
////
////    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
////    /**
////     * Handles the HTTP <code>GET</code> method.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Handles the HTTP <code>POST</code> method.
////     *
////     * @param request servlet request
////     * @param response servlet response
////     * @throws ServletException if a servlet-specific error occurs
////     * @throws IOException if an I/O error occurs
////     */
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
////
////    /**
////     * Returns a short description of the servlet.
////     *
////     * @return a String containing servlet description
////     */
////    @Override
////    public String getServletInfo() {
////        return "Short description";
////    }// </editor-fold>
////
////}
//package com.servlet;
//
//import com.data.BlogsData;
//import com.entities.Blogs;
//import com.entities.User;
//import com.helper.ConnectionProvider;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class DeleteBlogServlet extends HttpServlet {
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        // Get the blog id from the request parameter
//        int bid = Integer.parseInt(request.getParameter("bid"));
//
//        // Get the current session
//        HttpSession session = request.getSession();
//        User u = (User) session.getAttribute("currentUser");
//        int uid = u.getId();
//
//        // Create a connection to the database using the ConnectionProvider class
////        ConnectionProvider connectionProvider = new ConnectionProvider();
//        BlogsData blogsData = new BlogsData(ConnectionProvider.getConnection());
//
//        // Get the blog object from the database using the blog id
//        Blogs blog = blogsData.getBlogById(bid);
//
//        // Check if the user is the owner of the blog
//        if (blog.getUid() == uid) {
//
//            try {
//
//                // Delete the blog from the database
//                boolean result = blogsData.deleteBlogById(bid);
//
//                if (result) {
//                    // Delete the blog image from the server
//                    String path = blog.getPic();
//                    File file = new File(path);
//                    file.delete();
//
//                    out.println("success");
//                } else {
//                    out.println("fail");
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            out.println("access denied");
//        }
//    }
//}
//------------------------------
package com.servlet;

import com.data.BlogsData;
import com.entities.Blogs;
import com.entities.User;
import com.helper.ConnectionProvider;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteBlogServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Get the blog id from the request parameter
        int bid = Integer.parseInt(request.getParameter("bid"));

        // Get the current session
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("currentUser");
        int uid = u.getId();

        // Create a connection to the database using the ConnectionProvider class
        BlogsData blogsData = new BlogsData(ConnectionProvider.getConnection());

        // Get the blog object from the database using the blog id
        Blogs blog = blogsData.getBlogById(bid);

        // Check if the user is the owner of the blog
        if (blog.getUid() == uid) {

            try {

                // Delete the blog from the database
                boolean result = blogsData.deleteBlogById(bid);

                if (result) {
                    // Delete the blog image from the server
                    String path = blog.getPic();
                    File file = new File(path);
                    if (file.exists()) {
                        file.delete();
                        out.println("success");
                    } else {
                        out.println("fail");
                    }
                } else {
                    out.println("fail");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            out.println("access denied");
        }
    }
}
