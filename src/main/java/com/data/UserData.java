package com.data;

import com.entities.User;
import java.sql.*;

public class UserData {

    private Connection conn;

    public UserData(Connection conn) {
        this.conn = conn;
    }

    // method to insert user in to database
    public boolean saveUser(User user) {

        boolean f = false;

        try {
            String insertquery = "insert into users(name, email, password) values (?,?,?)";

            PreparedStatement pstmt = this.conn.prepareStatement(insertquery);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            pstmt.executeUpdate();

            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }

    // getting user by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;

        try {

            String selectquery = "select * from users where email=? and password=?";

            PreparedStatement pstmt = conn.prepareStatement(selectquery);

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {

                //creating user obj
                user = new User();

                //getting form database
                String name = set.getString("name");

                //set to user obj
                user.setName(name);

                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    
    // getting user by email and password
    public String getNameById(int id) {
        
        String name = "";
        try {

            String selectquery = "select * from users where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(selectquery);

            pstmt.setInt(1, id);

            ResultSet set = pstmt.executeQuery();

            if (set.next()) {
                
                name = set.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public boolean emailExists(String email) throws SQLException {

        String CHECK_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL_QUERY)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}
