package com.example.math_app.model;

public class model_resgister {
    private int id;
    private String username;
    private  String email;
    private String pasword;

    @Override
    public String toString() {
        return "model_resgister{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pasword='" + pasword + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public model_resgister(int id, String username, String email, String pasword) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pasword = pasword;
    }
}
