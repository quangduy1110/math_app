package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class menu extends AppCompatActivity {
    private Button bt_practice, bt_exam, bt_lesson;
    private ImageView img_home_menu, img_singout;
    private ProgressDialog progressDialog;
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

        img_singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(menu.this, login.class));
                        finish();
                    }
                }, 2000);


            }
        });
        progressDialog.dismiss();

        bt_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, list_topic_exam.class));
                finish();
            }
        });

        bt_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this, choose_style.class));
                onStop();
                finish();
            }
        });

    }

    private void initUI() {
        img_home_menu = findViewById(R.id.img_home_menu);
        img_singout = findViewById(R.id.img_dangxuat);
        bt_practice = findViewById(R.id.bt_luyentap);
        bt_exam = findViewById(R.id.bt_kiemtra);
        bt_lesson = findViewById(R.id.bt_lythuyet);
        progressDialog = new ProgressDialog(this);

    }
}