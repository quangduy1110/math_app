package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
 private Button bt_login, bt_register;
 private TextView tv_register;
 private ProgressBar progress_Bar;
 int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_Bar.setVisibility(View.VISIBLE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        progress_Bar.setProgress(counter);
                        if (counter == 50){
                            timer.cancel();
                            startActivity(new Intent(MainActivity.this, login.class));

                        }
                    }
                };
                timer.schedule(timerTask,50, 50);
            }
        });




        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress_Bar.setVisibility(View.VISIBLE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        progress_Bar.setProgress(counter);
                        if (counter == 50){
                            timer.cancel();
                            startActivity(new Intent(MainActivity.this, register.class));

                        }
                    }
                };
                timer.schedule(timerTask,50, 50);
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, register.class));

            }
        });
    }

    private void initUI() {
        bt_login = findViewById(R.id.bt_dang_nhap);
        bt_register = findViewById(R.id.bt_dang_ky);
        tv_register = findViewById(R.id.tv_dk);
        progress_Bar = findViewById(R.id.progressBar);


    }
}