package com.data;

import com.helper.ConnectionProvider;
import com.entities.Blogs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogsData {

    private Connection conn;

    public BlogsData(Connection conn) {
        this.conn = conn;
    }

    // method to insert blog into the database
    public boolean saveBlog(Blogs blog) {
        boolean f = false;
        try {
            // Insert blog into the database
            String query = "INSERT INTO blogs(title, description, pic, uid) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getDescription());
            pstmt.setString(3, blog.getPic());
            pstmt.setInt(4, blog.getUid());

            pstmt.executeUpdate();
            f = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    // for selecting blogs by userid
    public Blogs getBlogById(int bid) {
        Blogs blog = null;
        try {
            String query = "SELECT * FROM blogs WHERE bid=?";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setInt(1, bid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String pic = rs.getString("pic");
                int uid = rs.getInt("uid");

                blog = new Blogs(bid, title, description, pic, uid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    public List<Blogs> getAllBlogsByuid(int uid) {
        List<Blogs> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM blogs WHERE uid=?";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            pstmt.setInt(1, uid);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int bid = rs.getInt("bid");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String pic = rs.getString("pic");

                Blogs blog = new Blogs(bid, title, description, pic, uid);
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // method to get all the blogs
    public List<Blogs> getAllBlogs() {
        List<Blogs> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM blogs";
            PreparedStatement pstmt = this.conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int bid = rs.getInt("bid");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String pic = rs.getString("pic");
                int uid = rs.getInt("uid");

                Blogs blog = new Blogs(bid, title, description, pic, uid);
                list.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // update blog by id
    public boolean updateBlogById(Blogs blog) {
        boolean status = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionProvider.getConnection();
            String query = "UPDATE blogs SET title=?, description=?, pic=? WHERE bid=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getDescription());
            pstmt.setString(3, blog.getPic());
            pstmt.setInt(4, blog.getBid());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // delete blog by id
    public boolean deleteBlogById(int bid) throws SQLException {
        boolean status = false;
        Connection conn = null;
        PreparedStatement ps = conn.prepareStatement("DELETE FROM blogs WHERE bid=?");
        ps.setInt(1, bid);
        int count = ps.executeUpdate();
        if (count == 1) {
            status = true;
        }
        ps.close();
        conn.close();
        return status;
    }

}
