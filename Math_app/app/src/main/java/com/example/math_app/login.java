package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private EditText edt_username_login, edt_pass_login;
    private TextView tv_register, tv_login;

    private ImageView img_home_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initUI();
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, menu.class));
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, forgot_password.class));
            }
        });

        img_home_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, MainActivity.class));
            }
        });
    }


    private void initUI() {
        edt_username_login = findViewById(R.id.edt_hoten_dangnhap);
        edt_pass_login = findViewById(R.id.edt_matkhau_dangnhap);
        tv_login = findViewById(R.id.bt_dangnhap);
        tv_register = findViewById(R.id.tv_quen_mk);
        img_home_login = findViewById(R.id.img_home_login);
    }
}