package com.example.desinglogin;

public class Class {
    private int postid;
    private int id;
    private String name;
    private String email;
    private String body;

    public Class(int postid, int id, String name, String email, String body) {
        this.postid = postid;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
