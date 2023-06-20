package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class choose_style extends AppCompatActivity {
    private Button bt_lythuyet, bt_xemvideo;
    private ImageView img_quaylai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_style);
        img_quaylai = findViewById(R.id.back_choose_stye);
        bt_lythuyet = findViewById(R.id.bt_hoclythuyet);
        bt_xemvideo = findViewById(R.id.xemvideo);
        img_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(choose_style.this, menu.class));
            }
        });

        bt_lythuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(choose_style.this, lessons.class));
            }
        });
        bt_xemvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(choose_style.this, list_video.class));
            }
        });
    }
}