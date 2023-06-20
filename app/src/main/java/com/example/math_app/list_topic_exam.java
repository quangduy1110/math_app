package com.example.math_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.math_app.Adapter.ListKiemTraAdapter;
import com.example.math_app.model.ListKiemTra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class list_topic_exam extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListKiemTraAdapter adapter;
    private List<ListKiemTra> kiemTraList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_topic_exam);
        recyclerView = findViewById(R.id.recycleview_topic);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kiemTraList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("KiemTra");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kiemTraList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ListKiemTra kiemTra = dataSnapshot.getValue(ListKiemTra.class);
                    kiemTraList.add(kiemTra);
                }
                adapter = new ListKiemTraAdapter(kiemTraList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}