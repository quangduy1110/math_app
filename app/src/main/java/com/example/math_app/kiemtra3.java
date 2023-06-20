package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.math_app.Adapter.KiemtraAdapter;
import com.example.math_app.model.Kiemtra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class kiemtra3 extends AppCompatActivity {
    private KiemtraAdapter kiemtraAdapter;
    private List<Kiemtra> kiemtraList;
    private DatabaseReference databaseReference;
    private Button bt_dapan1, bt_dapan2, bt_dapan3, bt_dapan4;
    private Button icon_true1, icon_false1, icon_true2, icon_false2, icon_true3, icon_false3, icon_true4, icon_false4;
    private ImageView img_next, img_hinhanh, speak;
    private TextView tv_noidung, tv_causo, tv_thoigian;
    private CountDownTimer countDownTimer;
    private boolean checkClick = false;
    private boolean isTimerRunning = false;
    private MediaPlayer sound_win,sound_lose, sound_correct, sound_wrong;
    private TextToSpeech textToSpeech;
    private static final long time = 30000;
    private LottieAnimationView anim_time;
    int diem = 0;
    int index = -1;

    private void checkAnswer(Button button, Button icon_true, Button icon_false){
        Kiemtra kiemtra = kiemtraList.get(index);
        String dapan = kiemtra.getDapan();
        if (!checkClick){
            checkClick = true;
            String clickdapan = button.getText().toString();
            if(clickdapan.equals(dapan)){
                icon_true.setVisibility(View.VISIBLE);
                icon_false.setVisibility(View.INVISIBLE);
                diem+=5;
                sound_correct.start();
            }else {
                icon_true.setVisibility(View.INVISIBLE);
                icon_false.setVisibility(View.VISIBLE);
                sound_wrong.start();
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
        setContentView(R.layout.exam);
        initUI();
        sound_win = MediaPlayer.create(this, R.raw.sound_win);
        sound_lose = MediaPlayer.create(this, R.raw.sound_lose);
        sound_correct = MediaPlayer.create(this, R.raw.sound_correct);
        sound_wrong = MediaPlayer.create(this, R.raw.wrong);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        kiemtraList = new ArrayList<>();
        kiemtraAdapter = new KiemtraAdapter(kiemtraList);
        ExamData();
        startTime();
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
                    Toast.makeText(kiemtra3.this,"Chưa chọn đáp án", Toast.LENGTH_SHORT).show();
                }
                if(checkClick){
                    if(index < kiemtraList.size()-1){
                        index++;
                        Kiemtra kiemtra = kiemtraList.get(index);
                        Picasso.get().load(kiemtra.getHinhanh()).into(img_hinhanh);
                        tv_noidung.setText(kiemtra.getNoidung());
                        bt_dapan1.setText(kiemtra.getDapan1());
                        bt_dapan2.setText(kiemtra.getDapan2());
                        bt_dapan3.setText(kiemtra.getDapan3());
                        bt_dapan4.setText(kiemtra.getDapan4());
                        int a = index+1;
                        tv_causo.setText("Câu số: " + a);
                        stopTime();
                        startTime();
                        textToSpeech.speak(tv_noidung.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                    else {
                        ExamData();
                        dialog();
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
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    private void dialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_win);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LottieAnimationView anim_win = dialog.findViewById(R.id.anim_win);
        dialog.setCancelable(false);
        TextView tv_diem = dialog.findViewById(R.id.tv_diem_dialog);
        TextView tv_stt = dialog.findViewById(R.id.tv_thongbao_dialog);
        Button bt_back = dialog.findViewById(R.id.bt_back_dialog_win);
        stopTime();
        tv_diem.setText(""+diem);
        if(diem >= 20){
            sound_win.start();
            tv_stt.setText("Bạn đã vượt qua bài kiểm tra");
            anim_win.playAnimation();
            anim_time.cancelAnimation();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textToSpeech.speak(tv_stt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }, 3000);
        }else {
            sound_lose.start();
            anim_time.cancelAnimation();
            tv_stt.setText("Bạn chưa vượt qua bài kiểm tra");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textToSpeech.speak(tv_stt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }, 3500);
        }
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(kiemtra3.this, menu.class));
            }
        });
        dialog.show();
    }
    private void ExamData(){
        databaseReference.child("chude3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kiemtraList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Kiemtra kiemtra = dataSnapshot.getValue(Kiemtra.class);
                    kiemtraList.add(kiemtra);
                }
                if(index < kiemtraList.size()-1){
                    index++;
                    Kiemtra kiemtra = kiemtraList.get(index);
                    Picasso.get().load(kiemtra.getHinhanh()).into(img_hinhanh);
                    tv_noidung.setText(kiemtra.getNoidung());
                    bt_dapan1.setText(kiemtra.getDapan1());
                    bt_dapan2.setText(kiemtra.getDapan2());
                    bt_dapan3.setText(kiemtra.getDapan3());
                    bt_dapan4.setText(kiemtra.getDapan4());
                    int a = index+1;
                    tv_causo.setText("Câu số: " + a);
                    textToSpeech.speak(tv_noidung.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
                    anim_time.playAnimation();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Lỗi: " + error.getMessage());
            }
        });
    }
    private void startTime(){
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long milliseconds) {
                long seconds = milliseconds/1000;
                tv_thoigian.setText(""+ seconds);
            }

            @Override
            public void onFinish() {
                Toast.makeText(kiemtra3.this, "Hết thời gian!", Toast.LENGTH_SHORT).show();
                dialog();
            }
        };
        countDownTimer.start();
        isTimerRunning = true;
    }
    private void stopTime(){
        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
            isTimerRunning = false;

        }
    }
    private void initUI() {
        tv_thoigian = findViewById(R.id.tv_time);
        tv_causo = findViewById(R.id.tv_cauhoiso_exam);
        tv_noidung = findViewById(R.id.tv_cauhoi_exam);
        img_hinhanh = findViewById(R.id.img_noidung_exam);
        img_next = findViewById(R.id.img_next_exam);
        bt_dapan1= findViewById(R.id.bt_da1_exam);
        bt_dapan2= findViewById(R.id.bt_da2_exam);
        bt_dapan3= findViewById(R.id.bt_da3_exam);
        bt_dapan4= findViewById(R.id.bt_da4_exam);
        icon_true1= findViewById(R.id.bt_true_da1_exam);
        icon_false1= findViewById(R.id.bt_false_da1_exam);
        icon_true2= findViewById(R.id.bt_true_da2_exam);
        icon_false2= findViewById(R.id.bt_false_da2_exam);
        icon_true3= findViewById(R.id.bt_true_da3_exam);
        icon_false3= findViewById(R.id.bt_false_da3_exam);
        icon_true4= findViewById(R.id.bt_true_da4_exam);
        icon_false4= findViewById(R.id.bt_false_da4_exam);
        speak = findViewById(R.id.img_speak);
        anim_time = findViewById(R.id.anim_time);
    }
}
