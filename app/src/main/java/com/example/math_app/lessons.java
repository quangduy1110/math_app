package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math_app.Adapter.ImageAdapter;
import com.example.math_app.model.LessonModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class lessons extends AppCompatActivity {
    private ImageView img_next ,img_back, img_backlayout;
    private ViewPager viewPager;
    private ImageAdapter adapter;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons);
        viewPager = findViewById(R.id.viewPagger);
        img_backlayout = findViewById(R.id.img_back_layout);
        img_back = findViewById(R.id.img_back);
        img_next = findViewById(R.id. img_next);
        adapter = new ImageAdapter(lessons.this);
        viewPager.setAdapter(adapter);
        img_backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(lessons.this, menu.class));
                finish();
            }
        });

        img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(getItem(+1), true);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(getItem(-1), true);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Hocbai");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    ArrayList<LessonModel> lessonModels = new ArrayList<>();
                    for (DataSnapshot lessonSnapshot : snapshot.getChildren()){
                        LessonModel lesson = lessonSnapshot.getValue(LessonModel.class);
                        lessonModels.add(lesson);
                    }
                    adapter.setLessonList(lessonModels);
                    adapter.notifyDataSetChanged();

            } else {
                Toast.makeText(lessons.this,"Không có gì", Toast.LENGTH_SHORT).show();
            }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Lỗi: " + error.getMessage());
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(viewPager.getCurrentItem() == 0){
                    img_back.setVisibility(View.INVISIBLE);
                }else {
                    img_back.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

}
private int getItem(int i){
        return viewPager.getCurrentItem()+i;
}

}