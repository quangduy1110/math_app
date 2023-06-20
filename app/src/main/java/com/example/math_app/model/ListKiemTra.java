package com.example.math_app.model;

public class ListKiemTra {
    private String hinhanh;
    private String tenchude;

    public ListKiemTra(){

    }

    public String getHinhanh() {
        return hinhanh;
    }

    public String getTenchude() {
        return tenchude;
    }

    public ListKiemTra(String hinhanh, String tenchude) {
        this.hinhanh = hinhanh;
        this.tenchude = tenchude;
    }
}
