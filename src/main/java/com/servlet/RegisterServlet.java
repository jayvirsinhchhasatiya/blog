package com.servlet;

import com.data.UserData;
import com.entities.User;
import com.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      
        //fatch all form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");

        if (!password.equals(cpassword)) {
            response.sendRedirect("registration.jsp?error=Passwords do not match");
            return;
        }
        
        
         //UserData class object
        UserData ud = new UserData(ConnectionProvider.getConnection());

        try {
            if (ud.emailExists(email)) {
                response.sendRedirect("registration.jsp?error=Email already exists");
                return;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendRedirect("registration.jsp?error=Something went wrong, please try again later.");
            return;
        }

//        create user object and set all properties to that object
        User user = new User(name, email, password);

        if (ud.saveUser(user)) {
            response.sendRedirect("login.jsp?message=Successfully registered");
        } else {
            response.sendRedirect("registration.jsp?error=some error");
        }

    }
}
