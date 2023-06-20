package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText edt_username_login, edt_pass_login;
    private TextView tv_register, tv_login;

    private ImageView img_home_login;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initUI();
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSingin();
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, register.class));
            }
        });

        img_home_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, MainActivity.class));
            }
        });
    }

    private void onclickSingin() {
        String str_email = edt_username_login.getText().toString().trim();
        String str_pass = edt_pass_login.getText().toString().trim();

        if (!TextUtils.isEmpty(str_email) && !TextUtils.isEmpty(str_pass)){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            progressDialog.show();
            auth.signInWithEmailAndPassword(str_email, str_pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(login.this, menu.class));
                                finishAffinity();
                            } else {

                                Toast.makeText(login.this, "Đăng nhập thất bại, kiểm tra lại thông tin",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(login.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    private void initUI() {
        edt_username_login = findViewById(R.id.edt_email_dangnhap);
        edt_pass_login = findViewById(R.id.edt_matkhau_dangnhap);
        tv_login = findViewById(R.id.bt_dangnhap);
        tv_register = findViewById(R.id.tv_dki_chuacotk);
        img_home_login = findViewById(R.id.img_home_login);
        progressDialog = new ProgressDialog(this);
    }
}