package com.example.math_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
 private Button bt_login, bt_register;
 private TextView tv_forgotpassword;


 private ProgressDialog progressDialog;

 private LottieAnimationView anim_load;
 int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dialog();
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;

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
                dialog();
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        if (counter == 50){
                            timer.cancel();
                            startActivity(new Intent(MainActivity.this, register.class));

                        }
                    }
                };
                timer.schedule(timerTask,50, 50);
            }
        });

        tv_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        if (counter == 50){
                            timer.cancel();
                            startActivity(new Intent(MainActivity.this, forgot_password.class));

                        }
                    }
                };
                timer.schedule(timerTask,50, 50);
            }
        });
    }
    private void dialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_load);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LottieAnimationView anim_load = dialog.findViewById(R.id.anim_load);
        anim_load.playAnimation();
        dialog.show();
    }

    private void initUI() {
        bt_login = findViewById(R.id.bt_dang_nhap);
        bt_register = findViewById(R.id.bt_dang_ky);
        tv_forgotpassword = findViewById(R.id.tv_forgotpassword);
//        anim_load = findViewById(R.id.anim_load);

        progressDialog = new ProgressDialog(this);


    }
}