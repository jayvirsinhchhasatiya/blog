package com.helper;

import java.sql.*;

public class ConnectionProvider {

    private static Connection conn;

    public static Connection getConnection() {
        try {

            if (conn == null) {
                // load driver
                Class.forName("com.mysql.jdbc.Driver");
                
                //create connection
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
