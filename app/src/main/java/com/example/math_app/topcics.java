package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class topcics extends AppCompatActivity {
    TextView tv_tapdem, tv_tinhnham, tv_dovui, tv_hinhhoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topcics);
        initUI();
        tv_tapdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(topcics.this, quesstion_count.class));
            }
        });

        tv_tinhnham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(topcics.this, mental_arithmetic.class));
            }
        });

        tv_dovui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(topcics.this, funny_question.class));
            }
        });

        tv_hinhhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(topcics.this, geometry.class));
            }
        });
    }

    private void initUI() {
        tv_tapdem = findViewById(R.id.tv_topic_tapdem);
        tv_tinhnham = findViewById(R.id.tv_topic_tinhnham);
        tv_dovui = findViewById(R.id.tv_topic_dovui);
        tv_hinhhoc = findViewById(R.id.tv_topic_hinhhoc);

    }
}