package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class menu extends AppCompatActivity {
    private Button bt_practice;
    private ImageView img_home_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        initUI();
        img_home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, MainActivity.class));
            }
        });

        bt_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, topcics.class));
            }
        });
    }

    private void initUI() {
        img_home_menu = findViewById(R.id.img_home_menu);
        bt_practice = findViewById(R.id.bt_luyentap);
    }
}