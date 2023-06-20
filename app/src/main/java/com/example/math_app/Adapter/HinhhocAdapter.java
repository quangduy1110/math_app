package com.example.math_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.math_app.R;
import com.example.math_app.model.Hinhhoc;

import java.util.List;

public class HinhhocAdapter extends RecyclerView.Adapter<HinhhocAdapter.HinhocViewHolder> {
    private List<Hinhhoc> hinhhocList;

    public HinhhocAdapter(List<Hinhhoc> hinhhocList) {
        this.hinhhocList = hinhhocList;
    }

    @NonNull
    @Override
    public HinhocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question, parent, false);
        return new HinhocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HinhocViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return hinhhocList.size();
    }

    public class HinhocViewHolder extends RecyclerView.ViewHolder{


        public HinhocViewHolder(@NonNull View itemView) {
            super(itemView);


        }

    }

 }