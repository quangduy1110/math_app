package com.example.math_app.model;

public class model_login {
    private int id;
    private String Username;
    private String Password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public model_login(int id, String username, String password) {
        id = id;
        Username = username;
        Password = password;
    }

    @Override
    public String toString() {
        return "model_login{" +
                "Id=" + id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
