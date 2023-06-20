package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math_app.Adapter.DovuiAdapter;
import com.example.math_app.model.Dovui;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class funny_question extends AppCompatActivity {
    private DovuiAdapter dovuiAdapter;
    private List<Dovui> dovuiList;
    private DatabaseReference databaseReference;
    private Button bt_dapan1, bt_dapan2, bt_dapan3, bt_dapan4;
    private Button icon_true1, icon_false1, icon_true2, icon_false2, icon_true3, icon_false3, icon_true4, icon_false4;
    private ImageView img_next, img_noidung, speak;
    private TextView tv_noidung, tv_causo;
    private TextToSpeech textToSpeech;

    private boolean checkClick = false;

    int index = -1;

    private void checkAnswer(Button button, Button icon_true, Button icon_false){
        Dovui dovui = dovuiList.get(index);
        String dapan = dovui.getDapan();
        if (!checkClick){
            checkClick = true;

            String clickdapan = button.getText().toString();
            if(clickdapan.equals(dapan)){
                icon_true.setVisibility(View.VISIBLE);
                icon_false.setVisibility(View.INVISIBLE);
            }else {
                icon_true.setVisibility(View.INVISIBLE);
                icon_false.setVisibility(View.VISIBLE);
            }
            //hiển thị đáp án đúng
            if(bt_dapan1.getText().toString().equals(dapan)){
                icon_true1.setVisibility(View.VISIBLE);
                icon_false1.setVisibility(View.INVISIBLE);
            } else if (bt_dapan2.getText().toString().equals(dapan)) {
                icon_true2.setVisibility(View.VISIBLE);
                icon_false2.setVisibility(View.INVISIBLE);
            } else if (bt_dapan3.getText().toString().equals(dapan)) {
                icon_true3.setVisibility(View.VISIBLE);
                icon_false3.setVisibility(View.INVISIBLE);
            } else if (bt_dapan4.getText().toString().equals(dapan)) {
                icon_true4.setVisibility(View.VISIBLE);
                icon_false4.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void resetAnswer(){
        checkClick = false;
        icon_true1.setVisibility(View.INVISIBLE);
        icon_false1.setVisibility(View.INVISIBLE);
        icon_true2.setVisibility(View.INVISIBLE);
        icon_false2.setVisibility(View.INVISIBLE);
        icon_true3.setVisibility(View.INVISIBLE);
        icon_false3.setVisibility(View.INVISIBLE);
        icon_true4.setVisibility(View.INVISIBLE);
        icon_false4.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        initUI();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        dovuiList = new ArrayList<>();
        dovuiAdapter = new DovuiAdapter(dovuiList);
        DovuiData();

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(new Locale("vi"));
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Không hỗ trợ ngỗn ngữ này.");
                    }else {
                        Log.e("TTS", "Khởi tạo không thành công");
                    }
                }
            }
        });
        bt_dapan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(bt_dapan1, icon_true1, icon_false1);

            }
        });

        bt_dapan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(bt_dapan2, icon_true2, icon_false2);

            }
        });

        bt_dapan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(bt_dapan3, icon_true3, icon_false3);

            }
        });

        bt_dapan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(bt_dapan4, icon_true4, icon_false4);

            }
        });

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkClick){
                    Toast.makeText(funny_question.this,"Chưa chọn đáp án", Toast.LENGTH_SHORT).show();
                }
                if(checkClick){
                    if(index < dovuiList.size()-1){
                        index++;
                        Dovui dovui = dovuiList.get(index);
                        Picasso.get().load(dovui.getCaudo()).into(img_noidung);
                        tv_noidung.setText(dovui.getNoidung());
                        bt_dapan1.setText(dovui.getDapan1());
                        bt_dapan2.setText(dovui.getDapan2());
                        bt_dapan3.setText(dovui.getDapan3());
                        bt_dapan4.setText(dovui.getDapan4());
                        int a = index+1;
                        tv_causo.setText("Câu số: " + a);
                        textToSpeech.speak(tv_noidung.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                    else {
                        showDialog();
                    }
                }
                resetAnswer();
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(tv_noidung.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    private void DovuiData() {
        databaseReference.child("Dovui").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dovuiList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Dovui dovui = dataSnapshot.getValue(Dovui.class);
                    dovuiList.add(dovui);
                }

                if(index < dovuiList.size()-1){
                    index++;
                    Dovui dovui = dovuiList.get(index);
                    Picasso.get().load(dovui.getCaudo()).into(img_noidung);
                    tv_noidung.setText(dovui.getNoidung());
                    bt_dapan1.setText(dovui.getDapan1());
                    bt_dapan2.setText(dovui.getDapan2());
                    bt_dapan3.setText(dovui.getDapan3());
                    bt_dapan4.setText(dovui.getDapan4());
                    int a = index+1;
                    tv_causo.setText("Câu số: " + a);
                    textToSpeech.speak(tv_noidung.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Lỗi: " + error.getMessage());
            }
        });
    }
    private void showDialog(){
        final Dialog dialog = new Dialog(funny_question.this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button bt_back = dialog.findViewById(R.id.bt_dialog_back);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(funny_question.this, menu.class));
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void initUI() {
        img_noidung = findViewById(R.id.img_noidung);
        tv_causo = findViewById(R.id.tv_cauhoiso);
        tv_noidung = findViewById(R.id.tv_cauhoi_question);
        img_next = findViewById(R.id.img_next);
        bt_dapan1= findViewById(R.id.bt_da1qs);
        bt_dapan2= findViewById(R.id.bt_da2qs);
        bt_dapan3= findViewById(R.id.bt_dapan3qs);
        bt_dapan4= findViewById(R.id.bt_dapan4qs);
        icon_true1= findViewById(R.id.icon_true_da1);
        icon_false1= findViewById(R.id.icon_false_da1);
        icon_true2= findViewById(R.id.icon_true_da2);
        icon_false2= findViewById(R.id.icon_false_da2);
        icon_true3= findViewById(R.id.icon_true_da3);
        icon_false3= findViewById(R.id.icon_false_da3);
        icon_true4= findViewById(R.id.icon_true_da4);
        icon_false4= findViewById(R.id.icon_false_da4);
        speak = findViewById(R.id.img_speak);
    }
}