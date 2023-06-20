package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
   private TextView tv_create_account;
   private ImageView img_home;
   private ProgressDialog progressDialog;

   private EditText edt_email_register, edt_password_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initUI();
        initListener();

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, MainActivity.class));
            }
        });

    }

    private void initListener() {
        tv_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickregister();
            }
        });
    }
    private void onClickregister(){
        String str_email = edt_email_register.getText().toString().trim();
        String str_pass = edt_password_register.getText().toString().trim();

        if (!TextUtils.isEmpty(str_email) && !TextUtils.isEmpty(str_pass)){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            progressDialog.show();
            auth.createUserWithEmailAndPassword(str_email, str_pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(new Intent(register.this, menu.class));
                                finishAffinity();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(register.this, "Đăng ký không thành công",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(register.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

        }


    }


    private void initUI() {
        img_home = findViewById(R.id.img_home_dk);
        tv_create_account = findViewById(R.id.bt_tao_tai_khoan);
        edt_email_register = findViewById(R.id.edt_email_dki);
        edt_password_register = findViewById(R.id.edt_pass_dki);
        progressDialog = new ProgressDialog(this);
    }
}