package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class register extends AppCompatActivity {
   private Button bt_create_account;
   private ImageView img_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initUI();

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, MainActivity.class));
            }
        });

    }

    private void initUI() {
        img_home = findViewById(R.id.img_home_dk);
    }
}