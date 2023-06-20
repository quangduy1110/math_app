package com.example.math_app.model;

public class LessonModel {
    private String imgUrl;

    public LessonModel(){

    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LessonModel(String imgURL) {
        this.imgUrl = imgURL;
    }


}
