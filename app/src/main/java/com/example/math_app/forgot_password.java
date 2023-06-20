package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    private ImageView img_forgot;
    private TextView tv_laylaipass;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        initUI();
        img_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(forgot_password.this, MainActivity.class));
            }
        });

        tv_laylaipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickforgotpassword();
            }
        });

    }

    private void onclickforgotpassword() {
        progressDialog.show();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = "nguyenduy2k1pro@gmail.com";
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(forgot_password.this,"Gửi lại mật khẩu thành công",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initUI() {
        img_forgot = findViewById(R.id.img_home_forgot_password);
        tv_laylaipass = findViewById(R.id.tv_laylaimlk);
        progressDialog = new ProgressDialog(this);
    }
}