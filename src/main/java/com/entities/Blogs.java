package com.entities;

public class Blogs {

    private int bid;
    private String title;
    private String description;
    private String pic;
    private int uid;

    public Blogs() {
    }

    public Blogs(int bid) {
        this.bid = bid;
    }

    public Blogs(String title, String description, String pic, int uid) {
        this.title = title;
        this.description = description;
        this.pic = pic;
        this.uid = uid;
    }

    public Blogs(int bid, String title, String description, String pic, int uid) {
        this.bid = bid;
        this.title = title;
        this.description = description;
        this.pic = pic;
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
